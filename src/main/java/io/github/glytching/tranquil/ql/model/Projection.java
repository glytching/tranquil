package io.github.glytching.tranquil.ql.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the state of a projection expressed in our QL. A {@code field} expression such as
 * {@code item.name as theName} or {@code 10 + item.value as valuePlusTen} will be represented in
 * this class' {@link #fields}, {@link #literals}. A sql parser implementation will create 1..* of
 * these as it walks a given select clause and then use these instances to create an executable
 * expression of that select clause.
 */
public class Projection {

  // field names expressed in a select clause element
  private final List<String> fields;
  // literals expressed in a select clause element
  private final List<Object> literals;
  // the (optional) alias for this select clause element
  private String alias;

  public Projection() {
    this.fields = new ArrayList<>();
    this.literals = new ArrayList<>();
  }

  public String getAlias() {
    return this.alias == null ? deriveKey() : this.alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  public List<Object> getLiterals() {
    return this.literals;
  }

  public void addField(String field) {
    this.fields.add(field);
  }

  public List<String> getFields() {
    return fields;
  }

  public void addLiteral(Object literal) {
    this.literals.add(literal);
  }

  public boolean hasFields() {
    return !fields.isEmpty();
  }

  public boolean hasLiterals() {
    return !this.literals.isEmpty();
  }

  /**
   * If there is no explicit alias (i.e. no alias was specified on the incoming expression) then we
   * derive one by 'string-ifying' the value, as represented by either {@link #literals} or {@link
   * #fields} or both.
   *
   * @return
   */
  private String deriveKey() {
    StringBuilder sb = new StringBuilder();

    if (hasLiterals()) {
      literals.forEach(sb::append);
    }
    if (hasFields()) {
      fields.forEach(sb::append);
    }

    return sb.length() == 0 ? "unknown" : sb.toString();
  }
}
