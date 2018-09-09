package io.github.glytching.tranquil.exception;

/**
 * Extends {@link TranquilException} to provide some identity and traceability for exceptions which
 * * arise when mapping into / our ot the serialised form.
 */
public class MappingException extends TranquilException {

  public MappingException(Exception ex) {
    super("Failed to serialize!", ex);
  }

  public MappingException(Exception ex, String json) {
    super(String.format("Failed to deserialize [%s]!", json), ex);
  }

  public MappingException(String message, Exception ex) {
    super(message, ex);
  }
}
