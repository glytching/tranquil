package io.github.glytching.tranquil.cache;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CacheProviderTest {

  @Test
  public void willProvideASingleCacheInstance() {
    CacheProvider cacheProvider = new CacheProvider(1);
    Cache cache = cacheProvider.get();

    assertThat(cache, notNullValue());
    assertThat(cache, instanceOf(LRUCache.class));
    assertThat(cache, sameInstance(cacheProvider.get()));
  }
}
