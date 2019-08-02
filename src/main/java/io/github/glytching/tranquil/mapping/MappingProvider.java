package io.github.glytching.tranquil.mapping;

import io.github.glytching.tranquil.exception.MappingException;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface MappingProvider {

  /**
   * Parse the given {@code source} into a {@link Map}.
   *
   * @param source a source string
   * @return a {@link Map} representation of the given {@code source}
   */
  List<Map<String, Object>> deserialize(String source);

  /**
   * Parse a json string encapsulated in the given {@code sourceStream} into a {@link Map}.
   *
   * @param sourceStream an input stream containing a json string
   * @param charset
   * @return a {@link Map} representation of the given {@code sourceStream}
   */
  List<Map<String, Object>> deserialize(InputStream sourceStream, String charset);

  /**
   * Convert the {@code source} to a string.
   *
   * @param source the object to be converted
   * @return a string representation of the given {@code source}
   */
  String serialize(List<Map<String, Object>> source) throws MappingException;

  /**
   * Convert the {@code source} to an instance of the given {@code targetType}.
   *
   * @param source the object to be converted
   * @param targetType the type to which the given {@code source} should be converted
   * @param <T> the mapped result type
   * @return the converted object
   */
  <T> T serialize(List<Map<String, Object>> source, Class<T> targetType);

  /**
   * Convert the {@code source} to an instance of the given {@code targetType}.
   *
   * @param source the object to be converted
   * @param targetType the type to which the given {@code source} should be converted
   * @param <T> the mapped result type
   * @return the converted object
   */
  <T> T serialize(List<Map<String, Object>> source, TypeRef<T> targetType);
}
