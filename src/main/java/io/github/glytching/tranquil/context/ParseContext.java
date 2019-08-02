package io.github.glytching.tranquil.context;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Tranquil parses an input and reads from the parsed form of that input. For example: parse {@code
 * JSON} into a {@code Map} and then apply projections and predicates to that {@code Map}. This
 * interface defines the parsing features.
 */
public interface ParseContext {

  /**
   * Create a {@link ReadContext} for the given {@code source}. If you start with a {@code Map} then
   * use this as the entry point into Tranquil.
   *
   * @param source
   * @return
   */
  ReadContext parse(Map<String, Object> source);

  /**
   * Create a {@link ReadContext} for the given source string. If you start with a source string
   * then use this as the entry point into Tranquil.
   *
   * @param source
   * @return
   */
  ReadContext parse(String source);

  /**
   * Create a {@link ReadContext} for the given input stream. If you start with an input stream then
   * use this as the entry point into Tranquil.
   *
   * @param sourceStream
   * @return
   */
  ReadContext parse(InputStream sourceStream);

  /**
   * Create a {@link ReadContext} for the given input stream. If you start with an input stream, in
   * a specific charset, then use this as the entry point into Tranquil.
   *
   * @param sourceStream
   * @param charset
   * @return
   */
  ReadContext parse(InputStream sourceStream, String charset);

  /**
   * Create a {@link ReadContext} for the given file. If you start with a file then use this as the
   * entry point into Tranquil.
   *
   * @param sourceFile
   * @return
   * @throws IOException
   */
  ReadContext parse(File sourceFile) throws IOException;
}
