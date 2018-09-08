package io.github.glytching.tranquil;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.glytching.junit.extension.exception.ExpectedException;
import io.github.glytching.junit.extension.folder.TemporaryFolder;
import io.github.glytching.junit.extension.folder.TemporaryFolderExtension;
import io.github.glytching.tranquil.configuration.Configuration;
import io.github.glytching.tranquil.exception.MappingException;
import io.github.glytching.tranquil.exception.TranquilException;
import io.github.glytching.tranquil.mapping.GsonMappingProvider;
import io.github.glytching.tranquil.mapping.JacksonMappingProvider;
import io.github.glytching.tranquil.mapping.MappingProvider;
import io.github.glytching.tranquil.mapping.TypeRef;
import io.github.glytching.tranquil.util.Item;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static io.github.glytching.tranquil.configuration.Option.SUPPRESS_EXCEPTIONS;
import static io.github.glytching.tranquil.util.MapMaker.makeEntry;
import static io.github.glytching.tranquil.util.MapMaker.makeMap;
import static io.github.glytching.tranquil.util.TestData.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

/** Tests the public interface. */
@ExtendWith(TemporaryFolderExtension.class)
public class TranquilTest {

  private static TemporaryFolder TEMPORARY_FOLDER;
  private static final String EMPTY_JSON = "{}";

  @BeforeAll
  public static void prepare(TemporaryFolder givenTemporaryFolder) {
    TranquilTest.TEMPORARY_FOLDER = givenTemporaryFolder;
  }

  // given four JSON inputs, each of which has string, integer, float, date
  //    simple json object
  //    json array
  //    json object with single array attribute
  //    json object with combination of array and non array attributes

  // for each JSON verify:
  //    all operators: =, !=, <, >, <=, >=, like, not like, in, not in, null, not null
  //    all data types: string, float, integer, boolean, collections
  //    all conjunctions: AND, OR, AND with OR

  // we also cover:
  //    projections, including ...
  //      array accessors
  //      simple arithmetic: literals alone and literal plus accessor
  //      string literal concatenation: literals alone and literal plus accessor
  //    map to custom type, map to custom list
  //    all input mechanisms: String, InputStream, File (create a temp file on the fly?)
  //    all Mappers (jackson and Gson)
  //    exception handling: dodgy json, dodgy select, dodgy where
  //    exception handling suppression
  //    pretty printing

  @Test
  public void testAllOperatorsAndAllDataTypesWithSimpleJson() {
    assertThat(
        Tranquil.parse(SIMPLE_JSON)
            .read(
                "*",
                "quantity = 10 "
                    + "and quantity != 8 "
                    + "and quantity > 9 "
                    + "and quantity < 11 "
                    + "and quantity >= 10 "
                    + "and quantity <= 10 "
                    + "and price = 49.99 "
                    + "and price > 45.0 "
                    + "and price < 55.0 "
                    + "and price >= 49.0 "
                    + "and price <=50.0 "
                    + "and quantity in (5, 10) "
                    + "and quantity not in (20) "
                    + "and name = 'tap' "
                    + "and name like 'ta' "
                    + "and name not like 'ink' "
                    + "and since = '2018-09-07' "
                    + "and active = true "
                    + "and name is not null "
                    + "and owner is null"),
        is(SIMPLE_JSON));
  }

  @Test
  public void testAllOperatorsAndAllDataTypesWithComplexJson() {
    assertThat(
        Tranquil.parse(COMPLEX_JSON)
            .read(
                "*",
                "type = 'catalog' and items[*].quantity = 10 and items[0].name = 'tap'"),
        is(COMPLEX_JSON));
  }

  @Test
  public void testOrConjunction() {
    assertThat(
        Tranquil.parse(JSON_ARRAY).read("*", "quantity = 10 or name = 'sink'"), is(JSON_ARRAY));
  }

  @Test
  public void testAllConjunctions() {
    assertThat(
        Tranquil.parse(JSON_ARRAY).read("*", "quantity = 10 or active = true"), is(SIMPLE_JSON));
  }

  @Test
  public void testAllOperatorsAndAllDataTypesWithJsonArray() {
    assertThat(
        Tranquil.parse(JSON_ARRAY)
            .read(
                "*",
                "quantity = 10 "
                    + "and quantity != 8 "
                    + "and quantity > 9 "
                    + "and quantity < 11 "
                    + "and quantity >= 10 "
                    + "and quantity <= 10 "
                    + "and price = 49.99 "
                    + "and price > 45.0 "
                    + "and price < 55.0 "
                    + "and price >= 49.0 "
                    + "and price <=50.0 "
                    + "and quantity in (5, 10) "
                    + "and quantity not in (20) "
                    + "and name = 'tap' "
                    + "and name like 'ta' "
                    + "and name not like 'ink' "
                    + "and since = '2018-09-07' "
                    + "and active = true "
                    + "and name is not null "
                    + "and owner is null"),
        is(SIMPLE_JSON));
  }

  @Test
  public void testAllOperatorsAndAllDataTypesWithJsonHavingASingleArrayAttribute() {
    assertThat(
        Tranquil.parse(JSON_WITH_SINGLE_ARRAY_ATTRIBUTE)
            .read(
                "*",
                "quantity = 10 "
                    + "and quantity != 8 "
                    + "and quantity > 9 "
                    + "and quantity < 11 "
                    + "and quantity >= 10 "
                    + "and quantity <= 10 "
                    + "and price = 49.99 "
                    + "and price > 45.0 "
                    + "and price < 55.0 "
                    + "and price >= 49.0 "
                    + "and price <=50.0 "
                    + "and quantity in (5, 10) "
                    + "and quantity not in (20) "
                    + "and name = 'tap' "
                    + "and name like 'ta' "
                    + "and name not like 'ink' "
                    + "and since = '2018-09-07' "
                    + "and active = true "
                    + "and name is not null"
                    + " and owner is null"),
        is(String.format("{\"items\":[%s]}", SIMPLE_JSON)));
  }

  @Test
  public void testProjections() {
    assertThat(
        Tranquil.parse(SIMPLE_JSON).read("name, price, quantity", "name='tap'"),
        is("{\"name\":\"tap\",\"price\":49.99,\"quantity\":10}"));
  }

  @Test
  public void testProjectionsWithArrayAccessors() {
    assertThat(
        Tranquil.parse(COMPLEX_JSON)
            .read(
                "type, items[0].name as firstItemName, items[1].active as secondItemStatus",
                "type = 'catalog'"),
        is("{\"type\":\"catalog\",\"firstItemName\":\"tap\",\"secondItemStatus\":false}"));
  }

  @Test
  public void testProjectionsWithSimpleArithmetic() {
    assertThat(
        Tranquil.parse(SIMPLE_JSON)
            .read(
                "2 + 2 as four, 2 * price as doublePrice, 5 + quantity as quantityPlusFive, 20 - quantity as twentyMinusQuantity",
                "name='tap'"),
        is("{\"four\":4,\"doublePrice\":99.98,\"quantityPlusFive\":15,\"twentyMinusQuantity\":10}"));
  }

  @Test
  public void testProjectionsWithStringLiterals() {
    assertThat(
        Tranquil.parse(SIMPLE_JSON)
            .read(
                "'Laurel' + ' and ' + 'Hardy' as twosome, 'silver ' + name as qualifiedName",
                "name='tap'"),
        is("{\"twosome\":\"Laurel and Hardy\",\"qualifiedName\":\"silver tap\"}"));
  }

  @Test
  public void testReadAsAMap() {
    assertThat(
        Tranquil.parse(SIMPLE_JSON).read("name, price", "name='tap'", Map.class),
        is(makeMap(makeEntry("name", "tap"), makeEntry("price", 49.99))));
  }

  @Test
  public void testReadAsABespokeType() {
    assertThat(
        Tranquil.parse(SIMPLE_JSON).read("name, quantity", "name='tap'", Item.class),
        is(new Item("tap", 10)));
  }

  @Test
  public void testReadAsAGenericList() {
    TypeRef<List<Item>> type = new TypeRef<List<Item>>() {};

    List<Item> read = Tranquil.parse(JSON_ARRAY).read("name, quantity", "name!='foo'", type);

    assertThat(read.size(), is(2));
    assertThat(read, hasItem(new Item("tap", 10)));
    assertThat(read, hasItem(new Item("sink", 100)));
  }

  @Test
  public void testReadFromAMap() {
      Map map = Tranquil.parse(SIMPLE_JSON).read("", "", Map.class);

    assertThat(Tranquil.parse(map).read("name", "quantity=10"), is("{\"name\":\"tap\"}"));
  }

  @Test
  public void testReadFromInputStream() {
    assertThat(
        Tranquil.parse(toInputStream(SIMPLE_JSON)).read("*", "quantity = 10"), is(SIMPLE_JSON));
  }

  @Test
  public void testReadFromFile() throws IOException {
    File file = writeJsonToFile(SIMPLE_JSON);

    assertThat(Tranquil.parse(file).read("*", "quantity = 10"), is(SIMPLE_JSON));
  }

  @Test
  public void testReadWithGson() {
    assertThat(
        Tranquil.using(new GsonMappingProvider())
            .parse(GSON_SIMPLE_JSON)
            .read("*", "quantity = 10"),
        is(GSON_SIMPLE_JSON));
  }

  @ParameterizedTest
  @MethodSource("getMappingProviders")
  @ExpectedException(type = MappingException.class, messageIs = "Failed to deserialize [not json]!")
  public void willThrowIfTheJsonStringIsInvalid(MappingProvider mappingProvider) {
    Tranquil.using(mappingProvider).parse("not json");
  }

  @ParameterizedTest
  @MethodSource("getMappingProviders")
  @ExpectedException(type = MappingException.class, messageIs = "Failed to deserialize!")
  public void willThrowIfTheJsonStreamContainsInvalidJson(MappingProvider mappingProvider) {
    Tranquil.using(mappingProvider).parse(toInputStream("not json"));
  }

  @ParameterizedTest
  @MethodSource("getMappingProviders")
  @ExpectedException(type = MappingException.class, messageIs = "Failed to deserialize!")
  public void willThrowIfTheFileContainsInvalidJson(MappingProvider mappingProvider)
      throws IOException {
    File file = writeJsonToFile("not json");
    Tranquil.using(mappingProvider).parse(file);
  }

  @Test
  public void canSuppressInvalidJsonStringExceptions() {
    String out =
        Tranquil.using(Configuration.builder().options(SUPPRESS_EXCEPTIONS).build())
            .parse("not json")
            .read("", "");

    assertThat(out, is(EMPTY_JSON));
  }

  @Test
  @ExpectedException(
      type = TranquilException.class,
      messageIs =
          "Failed to read incoming due to [Failed to parse expression due to: [ no viable alternative at input '<EOF>' ] at position: 2 in line 1]!")
  public void willThrowIfTheSelectIsInvalid() {
    Tranquil.read("a,", SIMPLE_JSON, "");
  }

  @Test
  public void canSuppressExceptionsWhenThenSelectIsInvalid() {
    String read =
        Tranquil.using(Configuration.builder().options(SUPPRESS_EXCEPTIONS).build())
            .parse(SIMPLE_JSON)
            .read("a,", "");

    assertThat(read, is(EMPTY_JSON));
  }

  @Test
  @ExpectedException(
      type = TranquilException.class,
      messageIs =
          "Failed to read incoming due to [Failed to parse expression due to: [ no viable alternative at input 'x+' ] at position: 2 in line 1]!")
  public void willThrowIfTheWhereIsInvalid() {
    Tranquil.read("", SIMPLE_JSON, "x+");
  }

  @Test
  public void canSuppressExceptionsWhenThenWhereIsInvalid() {
    String read =
        Tranquil.using(Configuration.builder().options(SUPPRESS_EXCEPTIONS).build())
            .parse(SIMPLE_JSON)
            .read("", "x+");

    assertThat(read, is(EMPTY_JSON));
  }

  @Test
  public void canParseJsonStringWithPrettyPrintingUsingJackson() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

    assertThat(
        Tranquil.using(new JacksonMappingProvider(objectMapper))
            .parse(SIMPLE_JSON)
            .read("name, quantity, price", ""),
        is("{\n  \"name\" : \"tap\",\n  \"quantity\" : 10,\n  \"price\" : 49.99\n}"));
  }

  @Test
  public void canParseJsonStringWithPrettyPrintingUsingGson() {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    assertThat(
        Tranquil.using(new GsonMappingProvider(gson))
            .parse(SIMPLE_JSON)
            .read("name, quantity, price", ""),
        is("{\n  \"name\": \"tap\",\n  \"quantity\": 10.0,\n  \"price\": 49.99\n}"));
  }

  private static Stream<MappingProvider> getMappingProviders() {
    return Stream.of(new JacksonMappingProvider(), new GsonMappingProvider());
  }

  private InputStream toInputStream(String json) {
    return new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
  }

  private File writeJsonToFile(String json) throws IOException {
    File file = TEMPORARY_FOLDER.createFile("testReadFromFile.json");
    try (PrintWriter out = new PrintWriter(file.getPath())) {
      out.println(json);
    }
    return file;
  }
}
