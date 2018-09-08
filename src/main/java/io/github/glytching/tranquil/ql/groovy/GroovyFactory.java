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

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyCodeSource;
import groovy.lang.GroovySystem;
import io.github.glytching.tranquil.ql.Predicator;
import io.github.glytching.tranquil.ql.Projector;
import io.github.glytching.tranquil.ql.parser.SelectClauseParser;
import io.github.glytching.tranquil.ql.parser.WhereClauseParser;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Responsible for creating a Groovy classes for a given expression. Why Groovy? so that they can be
 * created and applied dynamically.
 */
public class GroovyFactory {
  private static final Logger logger = Logger.getLogger(GroovyFactory.class.getName());

  private final GroovyClassLoader groovyClassLoader;
  private final GroovySelectClauseParser selectClauseParser;
  private final GroovyWhereClauseParser whereClauseParser;

  public GroovyFactory() {
    this(new GroovyClassLoader(), new GroovySelectClauseParser(), new GroovyWhereClauseParser());
  }

  public GroovyFactory(
      GroovyClassLoader groovyClassLoader,
      GroovySelectClauseParser selectClauseParser,
      GroovyWhereClauseParser whereClauseParser) {
    this.groovyClassLoader = groovyClassLoader;
    this.selectClauseParser = selectClauseParser;
    this.whereClauseParser = whereClauseParser;
  }

  /**
   * Delegates to the {@link WhereClauseParser} to parse the given expression, supplying a ql
   * which accepts callbacks from the parser and uses these to create a 'groovified' representation
   * of the given expression.
   *
   * @param expression a 'where clause'
   * @return a Groovy implementation of our Predicator, specific to the given expression
   */
  public Predicator createPredicator(String expression) throws GroovyFactoryException {
    String script = whereClauseParser.parse(expression);

    logger.fine(
        String.format(
            "From the the expression: %s comes the groovy function: [%s]", expression, script));

    // now compile the groovy class and get an instance
    return create(Predicator.class, script);
  }

  /**
   * Delegates to the {@link SelectClauseParser} to parse the given expression, supplying a ql
   * which accepts callbacks from the parser and uses these to create a 'groovified' representation
   * of the given expression.
   *
   * @param expression a 'project clause'
   * @return a Groovy implementation of our Projector, specific to the given expression
   */
  public Projector createProjector(String expression) throws GroovyFactoryException {
    String script = selectClauseParser.parse(expression);
    // String script = selectClauseParser.get(String.class, expression);

    logger.fine(
        String.format(
            "From the the expression: %s comes the groovy function: [%s]", expression, script));

    // now compile the groovy class and get an instance
    return create(Projector.class, script);
  }

  @SuppressWarnings("unchecked")
  private <T> T create(final Class<T> type, final String script) throws GroovyFactoryException {
    // TODO cache this?!
    // now to compile the groovy class and get an instance
    String name = UUID.randomUUID().toString();
    GroovyCodeSource groovyCodeSource = new GroovyCodeSource(script, name, "/groovy/shell");
    groovyCodeSource.setCachable(true);
    Class<?> clazz = groovyClassLoader.parseClass(groovyCodeSource);
    Object obj;
    try {
      obj = clazz.newInstance();
    } catch (InstantiationException | IllegalAccessException e) {
      logger.log(Level.WARNING, e.getMessage(), e);
      throw new GroovyFactoryException(
          String.format("Exception creating the %s class instance!", type.getSimpleName()), e);
    }
    for (Class<?> c : groovyClassLoader.getLoadedClasses()) {
      GroovySystem.getMetaClassRegistry().removeMetaClass(c);
    }
    groovyClassLoader.clearCache();
    return (T) obj;
  }
}
