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

import io.github.glytching.tranquil.antlr.SQLLexer;
import io.github.glytching.tranquil.antlr.SQLParser;
import io.github.glytching.tranquil.antlr.SQLParserListener;
import io.github.glytching.tranquil.exception.TranquilParserException;
import io.github.glytching.tranquil.ql.TranquilErrorListener;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public abstract class BaseParser {

  /**
   * This is ANTLR's recommended two stage parsing strategy:
   *
   * <ol>
   *   <li>Parse with {@link PredictionMode#SLL} and if this fails then move on to ...
   *   <li>Parse with {@link PredictionMode#LL}
   * </ol>
   */
  private static final PredictionMode DEFAULT_PREDICTION_MODE = PredictionMode.SLL;

  protected void parse(String expression, SQLParserListener sqlParserListener) {
    if (expression != null && expression.length() > 0) {
      try {
        // this listener is stateful so use a fresh one each time through the parse call
        TranquilErrorListener errorListener = new TranquilErrorListener();

        SQLParser parser = getSqlParser(expression, errorListener);
        ParserRuleContext entryPoint = getParserContext(parser);

        if (errorListener.hasErrors()) {
          // continue but with a stricter mode
          entryPoint = withStrictMode(parser).select_list();
        }

        parse(sqlParserListener, errorListener, entryPoint);
      } catch (Exception ex) {
        // catching the general Exception because the listener may have thrown a runtime
        throw new TranquilParserException(ex.getMessage(), ex);
      }
    }
  }

  /**
   * Extension point which allows clause-specific parsers to define the entry point for their own
   * clause. For example an {@code select} parser has a different entry point to a {@code where}
   * parser.
   *
   * @param parser
   * @return a {@link ParserRuleContext} representing the correct point in the tree for a given type
   *     of parser
   */
  protected abstract ParserRuleContext getParserContext(SQLParser parser);

  private SQLParser getSqlParser(String expression, TranquilErrorListener errorListener) {
    SQLLexer lexer = new SQLLexer(new ANTLRInputStream(expression));
    // replace the default error listener with our own impl
    lexer.removeErrorListeners();
    lexer.addErrorListener(errorListener);

    SQLParser parser = new SQLParser(new CommonTokenStream(lexer));
    // replace the default error listener with our own impl
    parser.removeErrorListeners();
    parser.addErrorListener(errorListener);
    parser.getInterpreter().setPredictionMode(DEFAULT_PREDICTION_MODE);

    return parser;
  }

  private SQLParser withStrictMode(SQLParser parser) {
    // if the faster (more lenient) mode fails then retry with the slower (stricter) mode
    parser.getInterpreter().setPredictionMode(PredictionMode.LL);

    return parser;
  }

  private void parse(
      SQLParserListener sqlParserListener,
      TranquilErrorListener errorListener,
      ParserRuleContext entryPoint) {
    ParseTreeWalker walker = new ParseTreeWalker();

    walker.walk(sqlParserListener, entryPoint);

    // if the parser call raised any exceptions then lets throw
    errorListener
        .getException()
        .ifPresent(
            ex -> {
              throw ex;
            });
  }
}
