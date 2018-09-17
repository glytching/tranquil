package io.github.glytching.tranquil.context;

import io.github.glytching.tranquil.configuration.Configuration;
import io.github.glytching.tranquil.configuration.Option;
import io.github.glytching.tranquil.exception.TranquilException;
import io.github.glytching.tranquil.mapping.TypeRef;
import io.github.glytching.tranquil.ql.Predicator;
import io.github.glytching.tranquil.ql.Projector;
import io.github.glytching.tranquil.ql.groovy.GroovyFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Tranquil parses an input and reads from the parsed form of that input. For example: parse {@code
 * JSON} into a {@code Map} and then apply projections and predicates to that {@code Map}. This
 * class combines the parsing and reading behaviour.
 */
public class TranquilHandler implements ParseContext, ReadContext {

  private final Configuration configuration;
  private final GroovyFactory groovyFactory;
  private final boolean suppressExceptions;

  private List<Map<String, Object>> parsed;

  public TranquilHandler() {
    this(Configuration.defaultConfiguration());
  }

  public TranquilHandler(Configuration configuration) {
    this.configuration = configuration;
    this.suppressExceptions = configuration.containsOption(Option.SUPPRESS_EXCEPTIONS);
    this.groovyFactory = new GroovyFactory(configuration.lruCacheSize());
  }

  @Override
  public ReadContext parse(Map<String, Object> source) {
    this.parsed = Arrays.asList(source);
    return this;
  }

  @Override
  public ReadContext parse(String source) {
    this.parsed =
        executeWithExceptionHandling(() -> configuration.mappingProvider().deserialize(source));
    return this;
  }

  @Override
  public ReadContext parse(InputStream sourceStream) {
    return parse(sourceStream, "UTF-8");
  }

  @Override
  public ReadContext parse(InputStream sourceStream, String charset) {
    this.parsed =
        executeWithExceptionHandling(
            () -> configuration.mappingProvider().deserialize(sourceStream, charset));
    return this;
  }

  @Override
  public ReadContext parse(File sourceFile) throws IOException {
    try (FileInputStream fis = new FileInputStream(sourceFile)) {
      parse(fis);
    }
    return this;
  }

  @Override
  public boolean exists(String where) {
    boolean exists = true;
    List<Map<String, Object>> read = internalRead(parsed, "", where);

    if (!read.isEmpty()) {
      if (read.size() == 1) {
        // we might be dealing with an input which contained a single array attribute
        Object value = read.get(0).entrySet().iterator().next().getValue();
        if (value instanceof List) {
          exists = !((List) value).isEmpty();
        }
      }
    } else {
      exists = false;
    }
    return exists;
  }

  @Override
  public String read(String select, String where) {
    List<Map<String, Object>> read = internalRead(parsed, select, where);

    return configuration.mappingProvider().serialize(read);
  }

    @Override
    public String select(String select) {
        return read(select, "");
    }

    @Override
    public String where(String where) {
        return read("", where);
    }

    @Override
  public <T> T read(String select, String where, Class<T> type) {
    List<Map<String, Object>> read = internalRead(parsed, select, where);
    return configuration.mappingProvider().serialize(read, type);
  }

  @Override
  public <T> T read(String select, String where, TypeRef<T> type) {
    List<Map<String, Object>> read = internalRead(parsed, select, where);
    return configuration.mappingProvider().serialize(read, type);
  }

  private List<Map<String, Object>> internalRead(
      List<Map<String, Object>> incoming, String select, String where) {
    List<Map<String, Object>> read = new ArrayList<>();
    for (Map<String, Object> s : incoming) {
      Map<String, Object> handled = internalRead(s, select, where);
      if (!handled.isEmpty()) {
        read.add(handled);
      }
    }
    return read;
  }

  private Map<String, Object> internalRead(
      Map<String, Object> incoming, String select, String where) {
    try {
      Map<String, Object> response = new HashMap<>();

      if (isArray(incoming)) {
        Map.Entry<String, Object> entry = incoming.entrySet().iterator().next();
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> values = (List) entry.getValue();
        List<Map<String, Object>> matched = new ArrayList<>();

        for (Map<String, Object> value : values) {
          if (notEmpty(where)) {
            Predicator predicator = getPredicator(where.replaceAll(entry.getKey() + ".", ""));

            if (!predicator.isMatched(value)) {
              value = new HashMap<>();
            }
          }

          if (!value.isEmpty() && notEmpty(select)) {
            Projector projector = getProjector(select.replaceAll(entry.getKey() + ".", ""));

            value = projector.project(value);
          }

          if (!value.isEmpty()) {
            matched.add(value);
          }
        }
        response.put(entry.getKey(), matched);
        return response;
      } else {
        if (notEmpty(where)) {
          Predicator predicator = getPredicator(where);
          if (!predicator.isMatched(incoming)) {
            incoming = new HashMap<>();
          }
        }
        if (!incoming.isEmpty() && notEmpty(select)) {
          Projector projector = getProjector(select);

          incoming = projector.project(incoming);
        }
        return incoming;
      }
    } catch (Exception ex) {
      if (suppressExceptions) {
        return new HashMap<>();
      } else {
        throw new TranquilException(
            String.format("Failed to read incoming due to [%s]!", ex.getMessage()), ex);
      }
    }
  }

  private Predicator getPredicator(String expression) {
    return groovyFactory.createPredicator(expression);
  }

  private Projector getProjector(String expression) {
    return groovyFactory.createProjector(expression);
  }

  private boolean notEmpty(String incoming) {
    return incoming != null && incoming.length() > 0;
  }

  private boolean isArray(Map<String, Object> incoming) {
    if (incoming.size() == 1) {
      Map.Entry<String, Object> entry = incoming.entrySet().iterator().next();
      Object value = entry.getValue();
      return Collection.class.isAssignableFrom(value.getClass());
    } else {
      return false;
    }
  }

  @SuppressWarnings("unchecked")
  private <K> K executeWithExceptionHandling(Callable<K> callable) {
    try {
      return callable.call();
    } catch (RuntimeException ex) {
      if (suppressExceptions) {
        return (K) new ArrayList<>();
      } else {
        throw ex;
      }
    } catch (Exception ex) {
      if (suppressExceptions) {
        return (K) new ArrayList<>();
      } else {
        throw new TranquilException(ex);
      }
    }
  }
}
