package io.github.glytching.tranquil.configuration;

import io.github.glytching.tranquil.mapping.JacksonMappingProvider;
import io.github.glytching.tranquil.mapping.MappingProvider;

import java.util.EnumSet;
import java.util.Set;

/** Encapsulates the configuration defaults. */
public class DefaultsImpl implements Defaults {

  public static final DefaultsImpl INSTANCE = new DefaultsImpl();

  private DefaultsImpl() {}

  @Override
  public Set<Option> options() {
    return EnumSet.noneOf(Option.class);
  }

  @Override
  public MappingProvider mappingProvider() {
    return new JacksonMappingProvider();
  }
}
