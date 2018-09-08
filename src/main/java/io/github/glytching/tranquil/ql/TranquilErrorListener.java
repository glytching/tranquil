/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.glytching.tranquil.ql;

import io.github.glytching.tranquil.exception.TranquilParserException;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;

/**
 * An implementation of the ANTLR error listener which gathers <b>all</b> syntax errors in a given
 * expression.
 */
public class TranquilErrorListener extends BaseErrorListener {
  private static final String SYNTAX_ERROR_MESSAGE_TEMPLATE = "[ %s ] at position: %s in line %s";

  private final List<String> errorMessages = new ArrayList<>();

  @Override
  public void syntaxError(
      Recognizer<?, ?> recognizer,
      Object offendingSymbol,
      int line,
      int charPositionInLine,
      String msg,
      RecognitionException e) {
    errorMessages.add(format(SYNTAX_ERROR_MESSAGE_TEMPLATE, msg, charPositionInLine, line));
  }

  /**
   * If this listener contains any errors then return them in the form of a {@link
   * TranquilParserException}.
   *
   * @return
   */
  public Optional<TranquilParserException> getException() {
    if (!errorMessages.isEmpty()) {
      return Optional.of(
          // gather the root cause(s) in a comma delimited string
          new TranquilParserException(
              "Failed to parse expression due to: "
                  + errorMessages.stream().collect(Collectors.joining(", "))));
    }
    return Optional.empty();
  }

  public boolean hasErrors() {
    return !errorMessages.isEmpty();
  }
}
