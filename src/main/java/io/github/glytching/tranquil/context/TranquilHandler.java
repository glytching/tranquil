package io.github.glytching.tranquil.context;

import io.github.glytching.tranquil.cache.Cache;
import io.github.glytching.tranquil.cache.CacheProvider;
import io.github.glytching.tranquil.configuration.Configuration;
import io.github.glytching.tranquil.configuration.Option;
import io.github.glytching.tranquil.exception.TranquilException;
import io.github.glytching.tranquil.ql.Predicator;
import io.github.glytching.tranquil.ql.Projector;
import io.github.glytching.tranquil.ql.groovy.GroovyFactory;
import io.github.glytching.tranquil.mapping.TypeRef;

import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;

public class TranquilHandler implements ParseContext, ReadContext {

  private final Configuration configuration;
  private final boolean suppressExceptions;
  private final GroovyFactory groovyFactory;
  private final Cache cache;

  private List<Map<String, Object>> asMap;

  public TranquilHandler() {
    this(Configuration.defaultConfiguration());
  }

  public TranquilHandler(Configuration configuration) {
    this.configuration = configuration;
    this.suppressExceptions = configuration.containsOption(Option.SUPPRESS_EXCEPTIONS);
    this.groovyFactory = new GroovyFactory();
    this.cache = CacheProvider.get(configuration.lruCacheSize());
  }

  @Override
  public ReadContext parse(Map<String, Object> source) {
    this.asMap = Arrays.asList(source);
    return this;
  }

  @Override
  public ReadContext parse(String source) {
    this.asMap =
        executeWithExceptionHandling(() -> configuration.mappingProvider().deserialize(source));
    return this;
  }

  @Override
  public ReadContext parse(InputStream sourceStream) {
    return parse(sourceStream, "UTF-8");
  }

  @Override
  public ReadContext parse(InputStream sourceStream, String charset) {
    this.asMap =
        executeWithExceptionHandling(
            () -> configuration.mappingProvider().deserialize(sourceStream, charset));
    return this;
  }

  @Override
  public ReadContext parse(File sourceFile) throws IOException {
    FileInputStream fis = null;
    try {
      fis = new FileInputStream(sourceFile);
      parse(fis);
    } finally {
      closeQuietly(fis);
    }
    return this;
  }

  @Override
  public String read(String select, String where) {
    return configuration.mappingProvider().serialize(internalRead(asMap, select, where));
  }

  @Override
  public <T> T read(String select, String where, Class<T> type) {
    return configuration.mappingProvider().serialize(internalRead(asMap, select, where), type);
  }

  @Override
  public <T> T read(String select, String where, TypeRef<T> type) {
    return configuration.mappingProvider().serialize(internalRead(asMap, select, where), type);
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
    //    Predicator predicator = cache.get(Predicator.class, expression);
    //    if (predicator == null) {
    //      predicator = groovyFactory.createPredicator(expression);
    //      cache.put(expression, predicator);
    //    }
    //    return predicator;
    return groovyFactory.createPredicator(expression);
  }

  private Projector getProjector(String expression) {
    //    Projector projector = cache.get(Projector.class, expression);
    //    if (projector == null) {
    //      projector = groovyFactory.createProjector(expression);
    //      cache.put(expression, projector);
    //    }
    //    return projector;
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

  private void closeQuietly(Closeable closeable) {
    try {
      if (closeable != null) {
        closeable.close();
      }
    } catch (IOException ignore) {
    }
  }

  @SuppressWarnings("unchecked")
  private <K> K executeWithExceptionHandling(Callable<K> callable) {
    try {
      return callable.call();
    } catch (Exception ex) {
      if (suppressExceptions) {
        return (K) new ArrayList<>();
      } else {
        if (ex instanceof RuntimeException) {
          throw (RuntimeException) ex;
        } else {
          throw new RuntimeException(ex);
        }
      }
    }
  }
}
