package io.github.glytching.tranquil.exception;

public class MappingException extends TranquilException {

  public MappingException(Exception ex) {
    super("Failed to serialize!", ex);
  }

  public MappingException(String message, Exception ex) {
    super(message, ex);
  }

  public MappingException(Exception ex, String json) {
    super(String.format("Failed to deserialize [%s]!", json), ex);
  }
}
