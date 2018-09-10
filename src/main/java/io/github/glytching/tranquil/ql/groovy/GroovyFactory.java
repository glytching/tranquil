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
import io.github.glytching.tranquil.cache.Cache;
import io.github.glytching.tranquil.cache.CacheProvider;
import io.github.glytching.tranquil.ql.Predicator;
import io.github.glytching.tranquil.ql.Projector;
import io.github.glytching.tranquil.ql.parser.SelectClauseParser;
import io.github.glytching.tranquil.ql.parser.WhereClauseParser;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tranquil is given caller defined expression of predicates and projections. To apply these
 * expressions Tranquil has to create some sort of dynamic <i>executable</i>, this is where Groovy
 * comes in.
 */
public class GroovyFactory {
  private static final Logger logger = Logger.getLogger(GroovyFactory.class.getName());

  private final GroovyClassLoader groovyClassLoader;
  private final GroovySelectClauseParser selectClauseParser;
  private final GroovyWhereClauseParser whereClauseParser;
  private final Cache cache;

  public GroovyFactory(int cacheSize) {
    this(
        new GroovyClassLoader(),
        new GroovySelectClauseParser(),
        new GroovyWhereClauseParser(),
        new CacheProvider(cacheSize).get());
  }

  public GroovyFactory(
      GroovyClassLoader groovyClassLoader,
      GroovySelectClauseParser selectClauseParser,
      GroovyWhereClauseParser whereClauseParser,
      Cache cache) {
    this.groovyClassLoader = groovyClassLoader;
    this.selectClauseParser = selectClauseParser;
    this.whereClauseParser = whereClauseParser;
    this.cache = cache;
  }

  /**
   * Delegates to the {@link WhereClauseParser} to parse the given expression, supplying a ql which
   * accepts callbacks from the parser and uses these to create a 'groovified' representation of the
   * given expression.
   *
   * @param expression a 'where clause'
   * @return a Groovy implementation of our Predicator, specific to the given expression
   */
  public Predicator createPredicator(String expression) throws GroovyFactoryException {
    Predicator predicator = cache.get(Predicator.class, expression);
    if (predicator == null) {
      String script = whereClauseParser.parse(expression);

      logger.log(
          Level.FINEST,
          "Created the groovy class: [{0}] from the expression: [{1}]",
          new Object[] {script, expression});

      predicator = create(Predicator.class, script);
      cache.put(expression, predicator);
    }

    return predicator;
  }

  /**
   * Delegates to the {@link SelectClauseParser} to parse the given expression, supplying a ql which
   * accepts callbacks from the parser and uses these to create a 'groovified' representation of the
   * given expression.
   *
   * @param expression a 'project clause'
   * @return a Groovy implementation of our Projector, specific to the given expression
   */
  public Projector createProjector(String expression) throws GroovyFactoryException {
    Projector projector = cache.get(Projector.class, expression);
    if (projector == null) {
      String script = selectClauseParser.parse(expression);

      logger.log(
          Level.FINEST,
          "Created the groovy class: [{0}] from the expression: [{1}]",
          new Object[] {script, expression});

      projector = create(Projector.class, script);
      cache.put(expression, projector);
    }
    return projector;
  }

  @SuppressWarnings("unchecked")
  private <T> T create(final Class<T> type, final String script) throws GroovyFactoryException {
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
