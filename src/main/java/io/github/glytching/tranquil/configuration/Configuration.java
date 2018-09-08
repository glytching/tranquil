package io.github.glytching.tranquil.configuration;

import io.github.glytching.tranquil.mapping.MappingProvider;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import static java.util.Arrays.asList;

public class Configuration {

  private static final Defaults DEFAULTS = DefaultsImpl.INSTANCE;

  private final MappingProvider mappingProvider;
  private final int lruCacheSize;
  private final Set<Option> options;

  private Configuration(
      MappingProvider mappingProvider, int lruCacheSize, EnumSet<Option> options) {
    this.mappingProvider = mappingProvider;
    this.lruCacheSize = lruCacheSize;
    this.options = Collections.unmodifiableSet(options);
  }

  /**
   * Creates a new default configuration
   *
   * @return a new configuration based on defaults
   */
  public static Configuration defaultConfiguration() {
    Defaults defaults = getEffectiveDefaults();
    return Configuration.builder()
        .mappingProvider(defaults.mappingProvider())
        .lruCacheSize(100)
        .options(defaults.options())
        .build();
  }

  /**
   * Creates a new builder to help construct a configuration.
   *
   * @return
   */
  public static ConfigurationBuilder builder() {
    return new ConfigurationBuilder();
  }

  /**
   * Returns the {@link MappingProvider} used by this configuration
   *
   * @return mappingProvider
   */
  public MappingProvider mappingProvider() {
    return mappingProvider;
  }

  /**
   * Returns the size of the LRU cache
   *
   * @return lruCacheSize
   */
  public int lruCacheSize() {
    return lruCacheSize;
  }

  /**
   * Does this configuration contain the given {@code option}?
   *
   * @param option option to check
   * @return true if this configuration contains the given {@code option}
   */
  public boolean containsOption(Option option) {
    return options.contains(option);
  }

  private static Defaults getEffectiveDefaults() {
    return DEFAULTS;
  }

  public static class ConfigurationBuilder {
    private MappingProvider mappingProvider;
    private EnumSet<Option> options = EnumSet.noneOf(Option.class);
    private int lruCacheSize;

    public ConfigurationBuilder mappingProvider(MappingProvider provider) {
      this.mappingProvider = provider;
      return this;
    }

    public ConfigurationBuilder lruCacheSize(int size) {
      lruCacheSize = size;
      return this;
    }

    public ConfigurationBuilder options(Option... options) {
      if (options.length > 0) {
        this.options.addAll(asList(options));
      }
      return this;
    }

    public ConfigurationBuilder options(Set<Option> options) {
      this.options.addAll(options);
      return this;
    }

    public Configuration build() {
      final Defaults defaults = getEffectiveDefaults();
      if (mappingProvider == null) {
        mappingProvider = defaults.mappingProvider();
      }
      if (options.isEmpty() && !defaults.options().isEmpty()) {
        options.addAll(defaults.options());
      }
      return new Configuration(mappingProvider, lruCacheSize, options);
    }
  }
}
