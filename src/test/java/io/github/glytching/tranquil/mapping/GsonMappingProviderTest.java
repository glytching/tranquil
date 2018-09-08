package io.github.glytching.tranquil.mapping;

import io.github.glytching.junit.extension.exception.ExpectedException;
import io.github.glytching.tranquil.exception.MappingException;
import io.github.glytching.tranquil.util.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import static io.github.glytching.tranquil.util.MapMaker.makeEntry;
import static io.github.glytching.tranquil.util.MapMaker.makeMap;
import static io.github.glytching.tranquil.util.TestData.*;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GsonMappingProviderTest {

  private MappingProvider sut;

  @BeforeEach
  public void setup() {
    sut = new GsonMappingProvider();
  }

  @Test
  public void canDeserializeSimpleJson() throws Exception {
    assertSimpleJsonDeserialization(() -> sut.deserialize(GSON_SIMPLE_JSON));

    assertSimpleJsonDeserialization(
        () -> sut.deserialize(toInputStream(GSON_SIMPLE_JSON), "UTF-8"));
  }

  @Test
  public void canDeserializeJsonArray() throws Exception {
    assertJsonArrayDeserialization(() -> sut.deserialize(GSON_JSON_ARRAY));

    assertJsonArrayDeserialization(() -> sut.deserialize(toInputStream(GSON_JSON_ARRAY), "UTF-8"));
  }

  @Test
  public void canDeserializeJsonWithSingleArrayAttribute() throws Exception {
    assertJsonArrayWithSingleArrayAttributeDeserialization(
        () -> sut.deserialize(GSON_JSON_WITH_SINGLE_ARRAY_ATTRIBUTE));

    assertJsonArrayWithSingleArrayAttributeDeserialization(
        () -> sut.deserialize(toInputStream(GSON_JSON_WITH_SINGLE_ARRAY_ATTRIBUTE), "UTF-8"));
  }

  @Test
  public void canDeserializeJsonWithMixtureOfArrayAndNonArrayAttributes() throws Exception {
    assertJsonArrayWithMixedAttributesDeserialization(() -> sut.deserialize(GSON_COMPLEX_JSON));
    assertJsonArrayWithMixedAttributesDeserialization(
        () -> sut.deserialize(toInputStream(GSON_COMPLEX_JSON), "UTF-8"));
  }

  @Test
  @ExpectedException(type = MappingException.class, messageIs = "Failed to deserialize [not json]!")
  public void willThrowIfGivenInvalidJson() {
    sut.deserialize("not json");
  }

  @Test
  @ExpectedException(type = MappingException.class, messageIs = "Failed to deserialize!")
  public void willThrowIfGivenAnInputStreamContainingInvalidJson() {
    sut.deserialize(toInputStream("not json"), "UTF-8");
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        GSON_SIMPLE_JSON,
        GSON_JSON_ARRAY,
        GSON_COMPLEX_JSON,
        GSON_JSON_WITH_SINGLE_ARRAY_ATTRIBUTE
      })
  public void canSerializeJson(String json) {
    List<Map<String, Object>> deserialized = sut.deserialize(json);

    assertThat(sut.serialize(deserialized), is(json));
  }

  @Test
  public void willSerializeAnEmptyCollectionToAnEmptyResponse() {
    assertThat(sut.serialize(Collections.emptyList()), is("{}"));

    assertThat(sut.serialize(Collections.emptyList(), Item.class), is(new Item()));

    assertThat(
        sut.serialize(Collections.emptyList(), new TypeRef<List<Item>>() {}),
        is(Collections.emptyList()));
  }

  @Test
  public void willReturnNullIfAskedToSerializeNull() {
    assertThat(sut.serialize(null), nullValue());

    assertThat(sut.serialize(null, Item.class), nullValue());

    assertThat(sut.serialize(null, new TypeRef<List<Item>>() {}), nullValue());
  }

  @Test
  public void canSerializeToABespokeType() {
    List<Map<String, Object>> deserialized = sut.deserialize(GSON_SIMPLE_JSON);
    Item serialized = sut.serialize(deserialized, Item.class);

    assertThat(serialized, is(new Item("tap", 10)));
  }

  @Test
  public void canSerializeToABespokeCollection() {
    List<Map<String, Object>> deserialized = sut.deserialize(GSON_JSON_ARRAY);

    TypeRef<List<Item>> type = new TypeRef<List<Item>>() {};

    List<Item> serialized = sut.serialize(deserialized, type);

    assertThat(serialized.size(), is(2));
    assertThat(serialized, hasItem(new Item("tap", 10)));
    assertThat(serialized, hasItem(new Item("sink", 100)));
  }

  @Test
  public void canSerializeJsonArray() {
    List<Map<String, Object>> deserialized = sut.deserialize(GSON_SIMPLE_JSON);

    assertThat(sut.serialize(deserialized), is(GSON_SIMPLE_JSON));
  }

  private InputStream toInputStream(String json) {
    return new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
  }

  private void assertSimpleJsonDeserialization(Callable<List<Map<String, Object>>> callable)
      throws Exception {
    List<Map<String, Object>> deserialized = callable.call();

    assertThat(deserialized.size(), is(1));
    assertThat(deserialized.get(0).size(), is(6));
    assertThat(deserialized.get(0), hasEntry("name", "tap"));
    assertThat(deserialized.get(0), hasEntry("quantity", 10.0));
    assertThat(deserialized.get(0), hasEntry("price", 49.99));
    assertThat(deserialized.get(0), hasEntry("active", true));
    assertThat(deserialized.get(0), hasEntry("owner", null));
    assertThat(deserialized.get(0), hasEntry("since", "2018-09-07"));
  }

  private void assertJsonArrayDeserialization(Callable<List<Map<String, Object>>> callable)
      throws Exception {
    List<Map<String, Object>> deserialized = callable.call();

    assertThat(deserialized.size(), is(2));
    assertThat(deserialized, hasItem(getTapItemAsMap()));
    assertThat(deserialized, hasItem(getSinkItemAsMap()));
  }

  private void assertJsonArrayWithSingleArrayAttributeDeserialization(
      Callable<List<Map<String, Object>>> callable) throws Exception {
    List<Map<String, Object>> deserialized = callable.call();

    List<Map<String, Object>> expectedItems = getExpectedItems();
    assertThat(deserialized.size(), is(1));
    assertThat(deserialized.get(0), hasEntry("items", expectedItems));
  }

  private void assertJsonArrayWithMixedAttributesDeserialization(
      Callable<List<Map<String, Object>>> callable) throws Exception {
    List<Map<String, Object>> deserialized = callable.call();

    List<Map<String, Object>> expectedItems = getExpectedItems();

    assertThat(deserialized.size(), is(1));
    assertThat(deserialized.get(0).size(), is(2));
    assertThat(deserialized.get(0), hasEntry("type", "catalog"));
    assertThat(deserialized.get(0), hasEntry("items", expectedItems));
  }

  private List<Map<String, Object>> getExpectedItems() {
    return asList(getTapItemAsMap(), getSinkItemAsMap());
  }

  private Map<String, Object> getSinkItemAsMap() {
    Map<String, Object> sink =
        makeMap(
            makeEntry("name", "sink"),
            makeEntry("quantity", 100.0),
            makeEntry("price", 99.99),
            makeEntry("active", false),
            makeEntry("since", "2018-09-02"));
    // cannot use makeEntry with a null value so 'put' it separately
    sink.put("owner", null);
    return sink;
  }

  private Map<String, Object> getTapItemAsMap() {
    Map<String, Object> tap =
        makeMap(
            makeEntry("name", "tap"),
            makeEntry("quantity", 10.0),
            makeEntry("price", 49.99),
            makeEntry("active", true),
            makeEntry("since", "2018-09-07"));
    // cannot use makeEntry with a null value so 'put' it separately
    tap.put("owner", null);
    return tap;
  }
}
