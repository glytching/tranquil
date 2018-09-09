package io.github.glytching.tranquil.ql.groovy;

import io.github.glytching.tranquil.exception.TranquilException;

/**
 * Extends {@link TranquilException} to provide some identity and traceability for exceptions which
 * arise from generating Groovy projections and predicates.
 */
public class GroovyFactoryException extends TranquilException {

  public GroovyFactoryException(String message, Throwable cause) {
    super(message, cause);
  }
}
