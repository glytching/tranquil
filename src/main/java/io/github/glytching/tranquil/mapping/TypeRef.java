package io.github.glytching.tranquil.mapping;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Used to specify generic type information for our {@link MappingProvider}. Example usage: <code>
 *     TypeRef<List<Item>> type = new TypeRef<List<Item>>(){};
 *
 *     List<Item> serialized = sut.map(incoming, type);
 * </code>
 *
 * <p>Adapted from <a
 * href="https://github.com/json-path/JsonPath/blob/master/json-path/src/main/java/com/jayway/jsonpath/TypeRef.java">JsonPath's
 * TypeRef</a>
 *
 * @param <T>
 */
public abstract class TypeRef<T> implements Comparable<TypeRef<T>> {
  protected final Type type;

  protected TypeRef() {
    Type superClass = getClass().getGenericSuperclass();
    if (superClass instanceof Class<?>) {
      throw new IllegalArgumentException("No type info in TypeRef");
    }
    type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
  }

  public Type getType() {
    return type;
  }

  /** Prevent construction of a reference without type information. */
  @Override
  public int compareTo(TypeRef<T> o) {
    return 0;
  }
}
