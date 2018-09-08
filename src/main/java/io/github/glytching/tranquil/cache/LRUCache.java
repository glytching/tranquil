package io.github.glytching.tranquil.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache implements Cache {

  private static final boolean SORT_BY_ACCESS = true;
  private static final float LOAD_FACTOR = 0.75F;

  private final Map<String, Object> delegate;
  private final int maxSize;

  public LRUCache(int maxSize) {
    this.maxSize = maxSize;
    this.delegate = new LinkedHashMap<>(maxSize, LOAD_FACTOR, SORT_BY_ACCESS);
  }

  @Override
  public Object get(String key) {
    return this.delegate.get(key);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T get(Class<T> clazz, String key) {
    return (T) get(key);
  }

  @Override
  public void put(String key, Object value) {
    if (this.delegate.containsKey(key)) {
      this.delegate.remove(key);
    } else if (this.delegate.size() >= this.maxSize) {
      if (this.delegate.keySet().iterator().hasNext()) {
        this.delegate.remove(this.delegate.keySet().iterator().next());
      }
    }

    this.delegate.put(key, value);
  }

  @Override
  public int size() {
    return delegate.size();
  }
}
