package io.github.glytching.tranquil.cache;

/** Singleton factory pattern over an instance of {@link Cache}. */
public class CacheProvider {
  /** the maximum permitted capacity of the cache */
  private final int cacheSize;
  private volatile Cache cache = null;

  public CacheProvider(int cacheSize) {
    this.cacheSize = cacheSize;
  }

  /**
   * Return a singleton instance of {@link Cache}
   *
   * @return
   */
  public Cache get() {
    if (cache == null) {
      synchronized (this) {
        if (cache == null) {
          cache = new LRUCache(cacheSize);
        }
      }
    }
    return cache;
  }
}
