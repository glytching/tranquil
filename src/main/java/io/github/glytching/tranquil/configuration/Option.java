package io.github.glytching.tranquil.configuration;

/** Declares configuration options. */
public enum Option {
  /**
   * Suppress all exceptions when evaluating an input. This covers exceptions at each of:
   *
   * <ul>
   *   <li>Parse the input
   *   <li>Apply the <code>where</code>
   *   <li>Apply the <code>select</code>
   * </ul>
   *
   * If an exception is thrown and this option is <code>on</code> then the eventual response will be
   * an empty String.
   */
  SUPPRESS_EXCEPTIONS
}
