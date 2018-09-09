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
package io.github.glytching.tranquil.ql.groovy;

import io.github.glytching.tranquil.ql.parser.BaseParser;
import io.github.glytching.tranquil.ql.parser.WhereClauseParser;

/**
 * A Groovy specific implementation of {@link WhereClauseParser}. Example usage:
 *
 * <pre>
 *  GroovySelectClauseParser parser = new GroovySelectClauseParser();
 *
 *  String script = parser.get("a = 1 and b != 'this'");
 * </pre>
 */
public class GroovyWhereClauseParser extends WhereClauseParser<String> {

  /**
   * Parse the given {@code expression} into a Groovy class which can execute that expression
   * against a given input.
   *
   * @param expression the select expression
   * @return a Groovy script which applies the given {@code expression}
   */
  @Override
  public String parse(String expression) {
    GroovyWhereClauseListener listener = new GroovyWhereClauseListener();

    parse(expression, listener);

    return listener.getScript();
  }
}
