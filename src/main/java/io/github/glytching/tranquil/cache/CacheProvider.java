package io.github.glytching.tranquil.cache;

/** Singleton factory pattern over an instance of {@link Cache}. */
public class CacheProvider {
  private static Cache INSTANCE;

  // make outside instantiation more difficult
  private CacheProvider() {}

  /**
   * Return a singleton instance of {@link Cache}
   *
   * @param size the maximum permitted capacity of the cache
   * @return
   */
  public static Cache get(int size) {
    if (INSTANCE == null) {
      synchronized (CacheProvider.class) {
        if (INSTANCE == null) {
          INSTANCE = new LRUCache(size);
        }
      }
    }
    return INSTANCE;
  }
}
