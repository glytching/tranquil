package io.github.glytching.tranquil.mapping;

import com.github.underscore.lodash.U;
import io.github.glytching.tranquil.Experimental;
import io.github.glytching.tranquil.exception.MappingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Experimental
public class XmlMappingProvider extends JacksonMappingProvider {

  @Override
  @SuppressWarnings("unchecked")
  public List<Map<String, Object>> deserialize(String source) {
    try {
      List<Map<String, Object>> r = new ArrayList<>();
      r.add((Map<String, Object>) U.fromXml(source));
      return r;
    } catch (Exception ex) {
      throw new MappingException(ex, source);
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Map<String, Object>> deserialize(InputStream sourceStream, String charset) {
    try {
      List<Map<String, Object>> r = new ArrayList<>();
      r.add((Map<String, Object>) U.fromXml(toString(sourceStream)));
      return r;
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
        return U.toXml(new HashMap<>());
      } else {
        if (source.size() == 1) {
          return U.toXml(source.get(0));
        } else {
          StringBuilder sb = new StringBuilder();
          for (Map<String, Object> s : source) {
            sb.append(U.toXml(s)).append(System.lineSeparator());
          }
          return sb.toString();
        }
      }
    } catch (Exception ex) {
      throw new MappingException(ex);
    }
  }

  private String toString(InputStream input) throws IOException {
    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
      return buffer.lines().collect(Collectors.joining(System.lineSeparator()));
    }
  }
}
