package io.github.glytching.tranquil.mapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.github.glytching.tranquil.exception.MappingException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class GsonMappingProvider implements MappingProvider {

  private final Gson gson;

  /** Create with a default {@link Gson}. */
  public GsonMappingProvider() {
    this(new GsonBuilder().serializeNulls().create());
  }

  /**
   * Create with the given {@link Gson}.
   *
   * @param gson
   */
  public GsonMappingProvider(Gson gson) {
    this.gson = gson;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Map<String, Object>> deserialize(String source) {
    try {
      // handle JSON of this form: {...} and this form: [{...}, {...}]
      Object deserialized = gson.fromJson(source, Object.class);
      if (deserialized instanceof Collection) {
        return (List) deserialized;
      } else {
        List<Map<String, Object>> response = new ArrayList<>();
        response.add((Map) deserialized);
        return response;
      }
    } catch (Exception ex) {
      throw new MappingException(ex, source);
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Map<String, Object>> deserialize(InputStream sourceStream, String charset) {
    try {
      // handle JSON of this form: {...} and this form: [{...}, {...}]
      Object deserialized =
          gson.fromJson(new InputStreamReader(sourceStream, charset), Object.class);
      if (deserialized instanceof Collection) {
        return (List) deserialized;
      } else {
        List<Map<String, Object>> response = new ArrayList<>();
        response.add((Map) deserialized);
        return response;
      }
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
        return gson.toJson(new HashMap<>());
      } else {
        if (source.size() == 1) {
          return gson.toJson(source.get(0));
        } else {
          return gson.toJson(source);
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
        return gson.getAdapter(targetType).fromJson(gson.toJson(new HashMap<>()));
      } else {
        if (source.size() == 1) {
          return gson.getAdapter(targetType).fromJson(gson.toJson(source.get(0)));
        } else {
          return gson.getAdapter(targetType).fromJson(gson.toJson(source));
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
    try {
      if (source.isEmpty()) {
        return (T)
            gson.getAdapter(TypeToken.get(targetType.getType()))
                .fromJson(gson.toJson(new ArrayList<>()));
      } else {
        if (source.size() == 1) {
          return (T)
              gson.getAdapter(TypeToken.get(targetType.getType()))
                  .fromJson(gson.toJson(source.get(0)));
        } else {
          return (T)
              gson.getAdapter(TypeToken.get(targetType.getType())).fromJson(gson.toJson(source));
        }
      }
    } catch (Exception ex) {
      throw new MappingException(ex);
    }
  }
}
