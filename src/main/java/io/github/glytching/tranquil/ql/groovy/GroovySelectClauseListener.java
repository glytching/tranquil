package io.github.glytching.tranquil.ql.groovy;

import io.github.glytching.tranquil.antlr.SQLParser;
import io.github.glytching.tranquil.ql.TranquilLoggingListener;
import io.github.glytching.tranquil.ql.model.Projection;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Listen to callbacks from our sql parser and uses them to create a Groovy implementation of {@link
 * io.github.glytching.tranquil.ql.Projector}.
 */
public class GroovySelectClauseListener extends TranquilLoggingListener {
  private static final String TAB = "\t";
  private static final String NEWLINE = "\n";
  private static final String SELECT_STAR = "return given;\n";

  // package protected to facilitate testing
  static final String GROOVY_SCRIPT_TEMPLATE =
      "package io.github.glytching.tranquil.ql\n"
          + "class GroovyProjector implements Projector {\n"
          + "    @Override\n"
          + "    Map<String, Object> project(Object given) {\n"
          + "       "
          + SELECT_STAR
          + "    }\n"
          + "}\n";
  private static final Map<String, String> controlCharacters;

  static {
    controlCharacters = new HashMap<>();
    controlCharacters.put(".", "?.");
    controlCharacters.put("[", "[");
    controlCharacters.put("]", "]");
  }

  private final List<Projection> projections = new ArrayList<>();
  private Projection currentProjection;
  private boolean inAsClause = false;
  private boolean inColumnReference = false;
  private boolean skipNextTerminal = false;

  @Override
  public void enterColumn_reference(@NotNull SQLParser.Column_referenceContext ctx) {
    super.enterColumn_reference(ctx);
    inColumnReference = true;
  }

  @Override
  public void exitColumn_reference(@NotNull SQLParser.Column_referenceContext ctx) {
    super.exitColumn_reference(ctx);
    inColumnReference = false;
  }

  @Override
  public void enterSelect_sublist(@NotNull SQLParser.Select_sublistContext ctx) {
    super.enterSelect_sublist(ctx);
    String text = ctx.getText();
    if (isNotSelectAll(text)) {
      currentProjection = new Projection();
      projections.add(currentProjection);
    }
  }

  @Override
  public void exitSelect_sublist(@NotNull SQLParser.Select_sublistContext ctx) {
    super.exitSelect_sublist(ctx);
    skipNextTerminal = true;
  }

  @Override
  public void enterIdentifier(@NotNull SQLParser.IdentifierContext ctx) {
    super.enterIdentifier(ctx);
    String text = ctx.getText();
    if (inAsClause) {
      currentProjection.setAlias(text);
      skipNextTerminal = true;
    }
  }

  @Override
  public void visitTerminal(@NotNull TerminalNode node) {
    super.visitTerminal(node);
    if (!skipNextTerminal) {
      if (currentProjection != null) {
        String text = node.getText();
        if (inColumnReference) {
          currentProjection.addField(text);
        } else {
          currentProjection.addLiteral(text);
        }
      }
    } else {
      skipNextTerminal = false;
    }
  }

  @Override
  public void enterAs_clause(@NotNull SQLParser.As_clauseContext ctx) {
    super.enterAs_clause(ctx);
    inAsClause = true;
    skipNextTerminal = true;
  }

  @Override
  public void exitAs_clause(@NotNull SQLParser.As_clauseContext ctx) {
    super.exitAs_clause(ctx);
    inAsClause = false;
  }

  public String getScript() {
    String expression = getExpression();
    if (expression.length() > 0) {
      return GROOVY_SCRIPT_TEMPLATE.replace(SELECT_STAR, expression);
    } else {
      return GROOVY_SCRIPT_TEMPLATE;
    }
  }

  private String getExpression() {
    StringBuilder sb = new StringBuilder();
    if (!projections.isEmpty()) {
      sb.append(tab(1)).append("def m = [:]").append(NEWLINE);

      sb.append(getExpressions());

      sb.append(tab(2)).append("return m").append(NEWLINE);
    }
    return sb.toString();
  }

  private String getExpressions() {
    StringBuilder sb = new StringBuilder();

    for (Projection projection : projections) {
      // open the line
      sb.append(tab(2)).append("m.put(");
      // add the key
      sb.append("\"").append(projection.getAlias()).append("\"");
      // prepare to add the value
      sb.append(", ");

      if (projection.hasLiterals() && projection.hasFields()) {
        // we have a combination of literal and object accessor e.g. 1 + a.b.c
        if (projection.getLiterals().size() > 1) {
          for (Object literal : projection.getLiterals()) {
            sb.append(literal);
          }
        }
        sb.append(toFieldAccessor(projection));
      } else if (projection.hasLiterals()) {
        // we have a literal
        sb.append(toLiteral(projection));
      } else if (projection.hasFields()) {
        // we have a field accessor
        sb.append(toFieldAccessor(projection));
      }

      // close the line
      sb.append(")").append(NEWLINE);
    }

    return sb.toString();
  }

  private String toLiteral(Projection projection) {
    StringBuilder sb = new StringBuilder();

    if (projection.getLiterals().size() > 1) {
      projection.getLiterals().forEach(sb::append);
    } else {
      sb.append(projection.getLiterals().get(0));
    }

    return sb.toString();
  }

  private String toFieldAccessor(Projection projection) {
    StringBuilder sb = new StringBuilder("given").append("?.");
    for (String field : projection.getFields()) {
      if (isControlCharacter(field) || isNumber(field)) {
        sb.append(transformControlCharacter(field));
      } else {
        sb.append(field);
      }
    }
    return sb.toString();
  }

  private String transformControlCharacter(String incoming) {
    if (controlCharacters.containsKey(incoming)) {
      return controlCharacters.get(incoming);
    }
    return incoming;
  }

  private boolean isControlCharacter(String accessor) {
    return controlCharacters.containsKey(accessor);
  }

  private boolean isNumber(String accessor) {
    return accessor.matches("-?\\d+(\\.\\d+)?");
  }

  private boolean isNotSelectAll(String text) {
    return text != null && text.length() > 0 && !"*".equals(text);
  }

  private String tab(int count) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < count; i++) {
      sb.append(TAB);
    }
    return sb.toString();
  }
}
