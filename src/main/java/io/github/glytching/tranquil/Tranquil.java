package io.github.glytching.tranquil;

import io.github.glytching.tranquil.configuration.Configuration;
import io.github.glytching.tranquil.context.TranquilHandler;
import io.github.glytching.tranquil.context.ParseContext;
import io.github.glytching.tranquil.context.ReadContext;
import io.github.glytching.tranquil.mapping.MappingProvider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class Tranquil {

  /**
   * This is a shortcut to {@link #parse(String)} following by a read on the resulting {@link
   * ReadContext}.
   *
   * @param select a select expression
   * @param from a parseable input
   * @param where a where expression
   * @return
   */
  public static String read(String select, String from, String where) {
    return parse(from).read(select, where);
  }

  /**
   * Creates a {@link ParseContext} for use in parsing a given input. Use this helper if you want to
   * parse with a non standard (@link Configuration).
   *
   * <p>For example: <code>
   *    Configuration.builder().mappingProvider(new GsonMappingProvider()).build());
   *    Tranquil.using(configuration).parse(...).read(...);
   * </code>
   *
   * @param configuration configuration to use when parsing
   * @return a parsing context encapsulating the given configuration
   */
  public static ParseContext using(Configuration configuration) {
    return new TranquilHandler(configuration);
  }

  /**
   * Creates a {@link ParseContext} for use in parsing a given input. Use this helper if you want to
   * parse with then given {@code mappingProvider}.
   *
   * @param mappingProvider the {@link MappingProvider} to be used when parsing a given input
   * @return a parsing context encapsulating the given {@code mappingProvider}
   */
  public static ParseContext using(MappingProvider mappingProvider) {
    return new TranquilHandler(Configuration.builder().mappingProvider(mappingProvider).build());
  }

  /**
   * Parse the given input using the default {@link Configuration} and returns a {@link ReadContext}
   * for evaluation
   *
   * @param input input
   * @return a document context
   */
  public static ReadContext parse(String input) {
    return new TranquilHandler().parse(input);
  }

  /**
   * Parse the given input using the default {@link Configuration} and returns a {@link ReadContext}
   * for evaluation
   *
   * @param input input
   * @return a document context
   */
  public static ReadContext parse(Map<String, Object> input) {
    return new TranquilHandler().parse(input);
  }

  /**
   * Parse the given input stream using the default {@link Configuration} and returns a {@link
   * ReadContext} for evaluation
   *
   * @param input input
   * @return a document context
   */
  public static ReadContext parse(InputStream input) {
    return new TranquilHandler().parse(input);
  }

  /**
   * Parse the contents of the given file using the default {@link Configuration} and returns a
   * {@link ReadContext} for evaluation
   *
   * @param input input
   * @return a document context
   */
  public static ReadContext parse(File input) throws IOException {
    return new TranquilHandler().parse(input);
  }
}
