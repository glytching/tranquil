package io.github.glytching.tranquil.util;

public class TestData {

  public static final String SIMPLE_JSON =
      "{"
          + "\"name\":\"tap\","
          + "\"price\":49.99,"
          + "\"quantity\":10,"
          + "\"active\":true,"
          + "\"owner\":null,"
          + "\"since\":\"2018-09-07\""
          + "}";

  public static final String JSON_ARRAY =
      "["
          + "{"
          + "\"name\":\"tap\","
          + "\"price\":49.99,"
          + "\"quantity\":10,"
          + "\"active\":true,"
          + "\"owner\":null,"
          + "\"since\":\"2018-09-07\"},"
          + "{"
          + "\"name\":\"sink\","
          + "\"price\":99.99,"
          + "\"quantity\":100,"
          + "\"active\":false,"
          + "\"owner\":null,"
          + "\"since\":\"2018-09-02\""
          + "}]";

  public static final String COMPLEX_JSON =
      "{\"type\":\"catalog\",\"items\":"
          + "["
          + "{"
          + "\"name\":\"tap\","
          + "\"price\":49.99,"
          + "\"quantity\":10,"
          + "\"active\":true,"
          + "\"owner\":null,"
          + "\"since\":\"2018-09-07\"},"
          + "{"
          + "\"name\":\"sink\","
          + "\"price\":99.99,"
          + "\"quantity\":100,"
          + "\"active\":false,"
          + "\"owner\":null,"
          + "\"since\":\"2018-09-02\""
          + "}]"
          + "}";

  public static final String JSON_WITH_SINGLE_ARRAY_ATTRIBUTE =
      "{\"items\":"
          + "["
          + "{"
          + "\"name\":\"tap\","
          + "\"price\":49.99,"
          + "\"quantity\":10,"
          + "\"active\":true,"
          + "\"owner\":null,"
          + "\"since\":\"2018-09-07\"},"
          + "{"
          + "\"name\":\"sink\","
          + "\"price\":99.99,"
          + "\"quantity\":100,"
          + "\"active\":false,"
          + "\"owner\":null,"
          + "\"since\":\"2018-09-02\""
          + "}]"
          + "}";

  // -- custom test data for Gson because of Gson's insistence on serializing all numerics as doubles

  public static final String GSON_SIMPLE_JSON =
      "{"
          + "\"name\":\"tap\","
          + "\"price\":49.99,"
          + "\"quantity\":10.0,"
          + "\"active\":true,"
          + "\"owner\":null,"
          + "\"since\":\"2018-09-07\""
          + "}";

  public static final String GSON_JSON_ARRAY =
      "["
          + "{"
          + "\"name\":\"tap\","
          + "\"price\":49.99,"
          + "\"quantity\":10.0,"
          + "\"active\":true,"
          + "\"owner\":null,"
          + "\"since\":\"2018-09-07\"},"
          + "{"
          + "\"name\":\"sink\","
          + "\"price\":99.99,"
          + "\"quantity\":100.0,"
          + "\"active\":false,"
          + "\"owner\":null,"
          + "\"since\":\"2018-09-02\""
          + "}]";

  public static final String GSON_COMPLEX_JSON =
      "{\"type\":\"catalog\",\"items\":"
          + "["
          + "{"
          + "\"name\":\"tap\","
          + "\"price\":49.99,"
          + "\"quantity\":10.0,"
          + "\"active\":true,"
          + "\"owner\":null,"
          + "\"since\":\"2018-09-07\"},"
          + "{"
          + "\"name\":\"sink\","
          + "\"price\":99.99,"
          + "\"quantity\":100.0,"
          + "\"active\":false,"
          + "\"owner\":null,"
          + "\"since\":\"2018-09-02\""
          + "}]"
          + "}";

  public static final String GSON_JSON_WITH_SINGLE_ARRAY_ATTRIBUTE =
      "{\"items\":"
          + "["
          + "{"
          + "\"name\":\"tap\","
          + "\"price\":49.99,"
          + "\"quantity\":10.0,"
          + "\"active\":true,"
          + "\"owner\":null,"
          + "\"since\":\"2018-09-07\"},"
          + "{"
          + "\"name\":\"sink\","
          + "\"price\":99.99,"
          + "\"quantity\":100.0,"
          + "\"active\":false,"
          + "\"owner\":null,"
          + "\"since\":\"2018-09-02\""
          + "}]"
          + "}";
}
