package io.github.glytching.tranquil.configuration;

import io.github.glytching.tranquil.mapping.MappingProvider;

import java.util.Set;

public interface Defaults {
  /**
   * Returns the default {@link Option}s
   *
   * @return default options
   */
  Set<Option> options();

  /**
   * Returns the default {@link MappingProvider}
   *
   * @return default mapping provider
   */
  MappingProvider mappingProvider();
}
