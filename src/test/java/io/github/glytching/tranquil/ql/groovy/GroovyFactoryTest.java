package io.github.glytching.tranquil.ql.groovy;

import io.github.glytching.tranquil.ql.Predicator;
import io.github.glytching.tranquil.ql.Projector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.github.glytching.tranquil.util.MapMaker.makeEntry;
import static io.github.glytching.tranquil.util.MapMaker.makeMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GroovyFactoryTest {

  private GroovyFactory sut;

  @BeforeEach
  public void setup() {
    sut = new GroovyFactory(2);
  }

  @Test
  public void canCreateAndCacheAPredicator() {
    Predicator predicator = sut.createPredicator("a > 1");

    Map<String, Object> incoming = makeMap(makeEntry("a", 5));
    assertThat(predicator.isMatched(incoming), is(true));
    assertThat(sut.createPredicator("a > 1"), sameInstance(predicator));
  }

  @Test
  public void canCreateAndCacheAProjector() {
    Projector projector = sut.createProjector("a");

    Map<String, Object> incoming = makeMap(makeEntry("a", 5), makeEntry("b", 10));
    Map<String, Object> projected = projector.project(incoming);
    assertThat(projected.size(), is(1));
    assertThat(projected, hasKey("a"));
    assertThat(projected.get("a"), is(5));

    assertThat(sut.createProjector("a"), sameInstance(projector));
  }

  @Test
  public void willCacheOnALRUBasis() {
    Predicator predicator = sut.createPredicator("a > 1");

    assertThat(sut.createPredicator("a > 1"), sameInstance(predicator));

    // create a few more predicators which should have the effect
    // of expiring our original predicator from the cache
    sut.createPredicator("b > 1");
    sut.createPredicator("c > 1");

    assertThat(sut.createPredicator("a > 1"), not(sameInstance(predicator)));
  }
}