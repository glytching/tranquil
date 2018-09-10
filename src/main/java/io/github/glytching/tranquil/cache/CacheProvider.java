package io.github.glytching.tranquil.cache;

/** Singleton factory pattern over an instance of {@link Cache}. */
public class CacheProvider {
  private Cache cache = null;

  /**
   * Return a singleton instance of {@link Cache}
   *
   * @param size the maximum permitted capacity of the cache
   * @return
   */
  public Cache get(int size) {
    Cache c = cache;
    if (c == null) {
      synchronized (this) {
        c = cache;
        if (c == null) {
          c = new LRUCache(size);
          cache = c;
        }
      }
    }
    return c;
  }
}
