package io.github.glytching.tranquil.cache;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LRUCacheTest {

  @Test
  public void cacheIsSizeLimitedAndDiscardsOldestFirst() {
    LRUCache sut = new LRUCache(2);

    sut.put("aKey", "aValue");
    sut.put("bKey", "bValue");

    assertThat(sut.size(), is(2));
    assertThat(sut.get("aKey"), is("aValue"));
    assertThat(sut.get("bKey"), is("bValue"));

    sut.put("cKey", "cValue");

    assertThat(sut.size(), is(2));
    assertThat(sut.get("bKey"), is("bValue"));
    assertThat(sut.get("cKey"), is("cValue"));

    sut.put("dKey", "dValue");
    sut.put("eKey", "eValue");

    assertThat(sut.size(), is(2));
    assertThat(sut.get("dKey"), is("dValue"));
    assertThat(sut.get("eKey"), is("eValue"));
  }
}
