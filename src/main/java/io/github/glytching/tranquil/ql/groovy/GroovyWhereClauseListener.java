package io.github.glytching.tranquil.ql.groovy;

import io.github.glytching.tranquil.antlr.SQLParser;
import io.github.glytching.tranquil.ql.TranquilLoggingListener;
import io.github.glytching.tranquil.ql.model.Predicate;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Listens to callbacks from our sql parser and uses them to create a Groovy implementation of
 * {@link io.github.glytching.tranquil.ql.Predicator}.
 */
public class GroovyWhereClauseListener extends TranquilLoggingListener {

  // package protected to facilitate testing
  static final String GROOVY_SCRIPT_TEMPLATE =
      "package io.github.glytching.tranquil.ql\n"
          + "class GroovyFilter implements Predicator {\n"
          + "\t@Override\n"
          + "\tboolean isMatched(Object given) {\n"
          + "\t\tboolean matched = true\n"
          + "\t\treturn matched\n"
          + "\t}\n"
          + "}\n";

  private static final String FIELD_SEPARATOR = ".";
  private static final String ROOT_ACCESSOR = "given" + FIELD_SEPARATOR;
  private static final String IN = "in";
  private static final String NOT = "not";
  private static final String LIKE = "like";
  private static final String NULL = "null";
  private static final String ARRAY_OPENING_BRACKETS = "[";
  private static final String ARRAY_CLOSING_BRACKETS = "]";
  private static final Map<String, String> conjunctionsMap;
  private static final Map<String, String> operatorsMap;

  static {
    conjunctionsMap = new HashMap<>();
    conjunctionsMap.put("and", "&&");
    conjunctionsMap.put("AND", "&&");
    conjunctionsMap.put("or", "||");
    conjunctionsMap.put("OR", "||");

    operatorsMap = new HashMap<>();
    operatorsMap.put("=", "==");
    operatorsMap.put("in", " in ");
    operatorsMap.put("IN", " in ");
    operatorsMap.put("like", "=~");
    operatorsMap.put("LIKE", "=~");
    operatorsMap.put("(", "[");
    operatorsMap.put(")", "]");
  }

  private final List<Predicate> predicates = new ArrayList<>();
  private Predicate currentPredicate = null;
  private boolean skipNextTerminal = false;
  private boolean inNullConstraint = false;

  @Override
  public void enterComp_op(@NotNull SQLParser.Comp_opContext ctx) {
    super.enterComp_op(ctx);

    String text = ctx.start.getText();
    if (operatorsMap.containsKey(text)) {
      text = operatorsMap.get(text);
    }
    currentPredicate.lhsComplete();
    currentPredicate.setOperator(text);
    skipNextTerminal = true;
  }

  @Override
  public void enterColumn_reference(@NotNull SQLParser.Column_referenceContext ctx) {
    super.enterColumn_reference(ctx);

    appendValueToCurrentPredicate(ROOT_ACCESSOR);
    skipNextTerminal = true;
  }

  @Override
  public void enterIdentifier(@NotNull SQLParser.IdentifierContext ctx) {
    super.enterIdentifier(ctx);
    skipNextTerminal = true;

    appendValueToCurrentPredicate(ctx.getText());
  }

  @Override
  public void visitTerminal(@NotNull TerminalNode node) {
    super.visitTerminal(node);
    String text = node.getText();
    if (!skipNextTerminal) {
      appendTerminal(text);
    } else {
      skipNextTerminal = false;
    }
  }

  @Override
  public void enterPredicate(@NotNull SQLParser.PredicateContext ctx) {
    super.enterPredicate(ctx);
    initialisePredicate();
  }

  @Override
  public void enterPattern_matcher(@NotNull SQLParser.Pattern_matcherContext ctx) {
    super.enterPattern_matcher(ctx);
    if (isNot(ctx.getText())) {
      currentPredicate.setNegated();
      skipNextTerminal = true;
    }
  }

  @Override
  public void enterNegativable_matcher(@NotNull SQLParser.Negativable_matcherContext ctx) {
    super.enterNegativable_matcher(ctx);
    if (isLike(ctx.getText())) {
      currentPredicate.setOperator(operatorsMap.get(ctx.getText()));
      skipNextTerminal = true;
    }
  }

  @Override
  public void enterAny_array_element(@NotNull SQLParser.Any_array_elementContext ctx) {
    super.enterAny_array_element(ctx);
    currentPredicate.setAnyCollectionElement();
  }

  @Override
  public void enterNull_predicate(@NotNull SQLParser.Null_predicateContext ctx) {
    super.enterNull_predicate(ctx);

    inNullConstraint = true;
    skipNextTerminal = true;
  }

  @Override
  public void exitNull_predicate(@NotNull SQLParser.Null_predicateContext ctx) {
    super.exitNull_predicate(ctx);

    currentPredicate.lhsComplete();
    currentPredicate.setOperator(operatorsMap.get("="));
    appendValueToCurrentPredicate(NULL);

    inNullConstraint = false;
    skipNextTerminal = false;
  }

  public String getScript() {
    String expression = getExpression();
    if (expression.length() > 0) {
      return GROOVY_SCRIPT_TEMPLATE.replace("true", expression);
    } else {
      return GROOVY_SCRIPT_TEMPLATE;
    }
  }

  private String getExpression() {
    StringBuilder sb = new StringBuilder();
    for (Predicate element : predicates) {
      String lhs = element.getLhs();
      if (lhs != null && lhs.length() > 0) {
        // to negate a predicate we have to wrap it in "!(...)"
        if (element.isNegated()) {
          sb.append("!(");
        }

        // handle the LHS, using Groovy's null safe operator
        sb.append(lhs.replace(".", "?."));
        // handle the operator and RHS, if any
        if (element.getOperator() != null) {
          sb.append(element.getOperator()).append(element.getRhs());
        }

        // close the negation, if necessary
        if (element.isNegated()) {
          sb.append(")");
        }

        if (element.getConjunction() != null) {
          sb.append(" ").append(element.getConjunction()).append(" ");
        }
      }
    }
    return sb.toString();
  }

  // it gets ugly here ...
  private void appendTerminal(String text) {
    if (isFieldSeparator(text)) {
      appendValueToCurrentPredicate(text);
    } else if (isIn(text)) {
      currentPredicate.setOperator(operatorsMap.get(text));
    } else if (isNot(text)) {
      currentPredicate.setNegated();
    } else if (!inNullConstraint()) {
      if (operatorsMap.containsKey(text)) {
        if (currentPredicate == null) {
          initialisePredicate();
        }
        appendValueToCurrentPredicate(operatorsMap.get(text));
      } else if (conjunctionsMap.containsKey(text)) {
        currentPredicate.setConjunction(conjunctionsMap.get(text));
        initialisePredicate();
      } else {
        appendValueToCurrentPredicate(text);
      }
    }
  }

  private void initialisePredicate() {
    currentPredicate = new Predicate();
    predicates.add(currentPredicate);
  }

  private boolean isFieldSeparator(String text) {
    return FIELD_SEPARATOR.equalsIgnoreCase(text);
  }

  private boolean isIn(String text) {
    return IN.equalsIgnoreCase(text);
  }

  private boolean isNot(String text) {
    return NOT.equalsIgnoreCase(text);
  }

  private boolean isLike(String text) {
    return LIKE.equalsIgnoreCase(text);
  }

  private boolean inNullConstraint() {
    return inNullConstraint;
  }

  private void appendValueToCurrentPredicate(String text) {
    if (shouldReplaceSurroundingQuotes(text)) {
      text = "\"" + text.substring(1, text.length() - 1) + "\"";
    }
    text = text.replace("''", "'");

    if (!currentPredicate.isLhsComplete()) {
      if (currentPredicate.isAnyCollectionElement()) {
        if (isOpeningArray(currentPredicate.getLhs())) {
          // if we are dealing with 'any element' then we need to Groovy's 'find element in
          // collection'
          currentPredicate.setLhs(
              currentPredicate.getLhs().replace("[", ".findResult(false){ v -> v"));
        } else {
          if (!isClosingArray(text)) {
            currentPredicate.appendToLhs(text);
          }
        }
      } else {
        currentPredicate.appendToLhs(text);
      }
    } else if (currentPredicate.getOperator() != null) {
      // done with the LHS, so everything belongs to the RHS now
      currentPredicate.appendToRhs(text);

      // close the 'find element in collection' lambda
      if (currentPredicate.isAnyCollectionElement()) {
        currentPredicate.appendToRhs(" }");
      }
    }
  }

  private boolean shouldReplaceSurroundingQuotes(String result) {
    return result.startsWith("\'") && result.endsWith("\'");
  }

  private boolean isOpeningArray(String incoming) {
    return incoming.endsWith(ARRAY_OPENING_BRACKETS);
  }

  private boolean isClosingArray(String incoming) {
    return ARRAY_CLOSING_BRACKETS.equals(incoming);
  }
}
