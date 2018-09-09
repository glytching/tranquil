package io.github.glytching.tranquil.exception;

/** The public exception type for Tranquil. */
// TODO consider whether this should extend Exception instead of RuntimeException
public class TranquilException extends RuntimeException {
  public TranquilException(String message, Throwable cause) {
    super(message, cause);
  }
}
