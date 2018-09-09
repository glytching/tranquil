package io.github.glytching.tranquil.cache;

/** Defines the basic features of a cache. */
public interface Cache {

  Object get(String key);

  <T> T get(Class<T> clazz, String key);

  void put(String key, Object value);

  int size();
}
