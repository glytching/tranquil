package io.github.glytching.tranquil.exception;

public class TranquilException extends RuntimeException {
  public TranquilException(String message) {
    super(message);
  }

  public TranquilException(String message, Throwable cause) {
    super(message, cause);
  }

  public TranquilException(Throwable cause) {
    super(cause);
  }
}
