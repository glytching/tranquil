package io.github.glytching.tranquil.mapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import io.github.glytching.tranquil.exception.MappingException;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JacksonMappingProvider implements MappingProvider {

  private static final ObjectMapper DEFAULT_OBJECT_MAPPER = new ObjectMapper();

  private final ObjectMapper objectMapper;
  private final ObjectReader objectReader;

  /** Create with a default {@link ObjectMapper}. */
  public JacksonMappingProvider() {
    this(DEFAULT_OBJECT_MAPPER);
  }

  /**
   * Create with the given {@link ObjectMapper}.
   *
   * @param objectMapper the ObjectMapper to be used
   */
  public JacksonMappingProvider(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    // handle JSON of this form: {...} and this form: [{...}, {...}]
    objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
    // ignore unmapped fields
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    this.objectReader = objectMapper.reader().forType(new TypeReference<List<Map>>() {});
  }

  @Override
  public List<Map<String, Object>> deserialize(String source) {
    try {
      return objectReader.readValue(source);
    } catch (Exception ex) {
      throw new MappingException(ex, source);
    }
  }

  @Override
  public List<Map<String, Object>> deserialize(InputStream sourceStream, String charset) {
    try {
      return objectReader.readValue(sourceStream);
    } catch (Exception ex) {
      throw new MappingException("Failed to deserialize!", ex);
    }
  }

  @Override
  public String serialize(List<Map<String, Object>> source) {
    if (source == null) {
      return null;
    }
    try {
      if (source.isEmpty()) {
        // the empty json string "{}"
        return objectMapper.writeValueAsString(new HashMap<>());
      } else {
        if (source.size() == 1) {
          // we were given a single json object so serialize back to that
          return objectMapper.writeValueAsString(source.get(0));
        } else {
          return objectMapper.writeValueAsString(source);
        }
      }
    } catch (Exception ex) {
      throw new MappingException(ex);
    }
  }

  @Override
  public <T> T serialize(List<Map<String, Object>> source, Class<T> targetType) {
    if (source == null) {
      return null;
    }
    try {
      if (source.isEmpty()) {
        // the empty json string "{}"
        return objectMapper.convertValue(new HashMap<>(), targetType);
      } else {
        if (source.size() == 1) {
          // we were given a single json object so serialize back to that
          return objectMapper.convertValue(source.get(0), targetType);
        } else {
          return objectMapper.convertValue(source, targetType);
        }
      }
    } catch (Exception ex) {
      throw new MappingException(ex);
    }
  }

  @Override
  public <T> T serialize(List<Map<String, Object>> source, TypeRef<T> targetType) {
    if (source == null) {
      return null;
    }
    JavaType type = objectMapper.getTypeFactory().constructType(targetType.getType());
    try {
      if (source.isEmpty()) {
        // the empty json string "{}"
        return objectMapper.convertValue(new HashMap<>(), type);
      } else {
        if (source.size() == 1) {
          // we were given a single json object so serialize back to that
          return objectMapper.convertValue(source.get(0), type);
        } else {
          return objectMapper.convertValue(source, type);
        }
      }
    } catch (Exception ex) {
      throw new MappingException(ex);
    }
  }
}
