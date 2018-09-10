package io.github.glytching.tranquil.mapping;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.github.glytching.tranquil.util.MapMaker.makeEntry;
import static io.github.glytching.tranquil.util.MapMaker.makeMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class XmlMappingProviderTest {

  private static final String XML_STRING =
      "<items>"
          + "<item><name>tap</name><price>10.50</price></item>"
          + "<item><name>sink</name><price>50.00</price></item>"
          + "</items>";

  private XmlMappingProvider sut;

  @BeforeEach
  public void setup() {
    sut = new XmlMappingProvider();
  }

  @Test
  public void canMapXmlString() {
    List<Map<String, Object>> mapped = sut.deserialize(XML_STRING);

    assertThat(mapped.size(), is(1));
    assertMappedResponse(mapped.get(0));
  }

  @Test
  public void canMapXmlInputStream() {
    List<Map<String, Object>> mapped =
        sut.deserialize(new ByteArrayInputStream(XML_STRING.getBytes()), "UTF-8");

    assertThat(mapped.size(), is(1));
    assertMappedResponse(mapped.get(0));
  }

  @Test
  public void canMapToXmlString() {
    Map<String, Object> source = makeMap(makeEntry("name", "joe"));

    String xml = sut.serialize(Arrays.asList(source));

    assertThat(xml, is("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<name>joe</name>"));
  }

  @Test
  public void canMapToMap() {
    Map<String, Object> source = makeMap(makeEntry("name", "joe"));

    Map actual = sut.serialize(Arrays.asList(source), Map.class);

    assertThat(actual, is(source));
  }

  private void assertMappedResponse(Map<String, Object> mapped) {
    assertThat(mapped.size(), is(1));

    assertThat(mapped, hasKey("items"));
    assertThat(mapped.get("items"), instanceOf(Map.class));

    Map<String, Object> inner = (Map) mapped.get("items");
    assertThat(inner, hasKey("item"));
    assertThat(inner.get("item"), instanceOf(List.class));

    // deserialize and collect the items for ease of assertion
    List<String> collected =
        ((List<Map<String, Object>>) inner.get("item"))
            .stream()
            .map(m -> m.get("name") + " = " + m.get("price"))
            .collect(Collectors.toList());
    assertThat(collected.size(), is(2));
    assertThat(collected, hasItem("tap = 10.50"));
    assertThat(collected, hasItem("sink = 50.00"));
  }
}
