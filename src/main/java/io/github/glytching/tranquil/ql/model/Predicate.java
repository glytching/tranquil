package io.github.glytching.tranquil.ql.model;

/**
 * Encapsulates the state of a predicate expressed in our QL. A {@code field <operator> value} pair
 * will be represented in this class' {@link #lhs}, {@link #operator}, {@link #rhs} state along with
 * any additional meta data such as operator negation, field conjunction etc. A sql parser
 * implementation will create 1..* of these as it walks a given where clause and then use these
 * instances to create an executable expression of that where clause.
 */
public class Predicate {
  private String lhs;
  private String operator;
  private String rhs;
  private boolean negated;
  private String conjunction;
  private boolean lhsComplete;
  private boolean anyCollectionElement;

  public boolean isNegated() {
    return negated;
  }

  public String getLhs() {
    return lhs;
  }

  public void setLhs(String lhs) {
    this.lhs = lhs;
  }

  public String getRhs() {
    return rhs;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String text) {
    this.operator = text;
    // if we have received an operator then the LHS must be terminated
    lhsComplete();
  }

  public String getConjunction() {
    return conjunction;
  }

  public void setConjunction(String text) {
    if (conjunction == null || conjunction.length() == 0) {
      this.conjunction = text;
    }
  }

  public void setNegated() {
    this.negated = true;
  }

  public void lhsComplete() {
    this.lhsComplete = true;
  }

  public void setAnyCollectionElement() {
    this.anyCollectionElement = true;
  }

  public boolean isLhsComplete() {
    return lhsComplete;
  }

  public boolean isAnyCollectionElement() {
    return anyCollectionElement;
  }

  public void appendToRhs(String s) {
    if (rhs == null) {
      rhs = s;
    } else {
      this.rhs = rhs + s;
    }
  }

  public void appendToLhs(String s) {
    if (lhs == null) {
      lhs = s;
    } else {
      this.lhs = lhs + s;
    }
  }
}
