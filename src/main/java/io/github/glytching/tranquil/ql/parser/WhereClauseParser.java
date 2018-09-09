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
package io.github.glytching.tranquil.ql.parser;

import io.github.glytching.tranquil.antlr.SQLParser;
import org.antlr.v4.runtime.ParserRuleContext;

/** Specialises {@link BaseParser} for {@code where} expressions */
public abstract class WhereClauseParser<T> extends BaseParser {

  /**
   * Parse the given {@code expression} into something which can <i>apply</i> the {@code
   * expression}.
   *
   * @param expression the where expression
   * @return an executable form of the given {@code expression}
   */
  public abstract T parse(String expression);

  @Override
  protected ParserRuleContext getParserContext(SQLParser parser) {
    // this is the entry point for a where clause
    return parser.search_condition();
  }
}
