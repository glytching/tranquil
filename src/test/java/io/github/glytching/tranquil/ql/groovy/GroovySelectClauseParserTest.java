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

import io.github.glytching.junit.extension.exception.ExpectedException;
import io.github.glytching.tranquil.exception.TranquilParserException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class GroovySelectClauseParserTest {

  private GroovySelectClauseParser sut = new GroovySelectClauseParser();

  @Test
  public void testSimpleSelect() {
    String parsed = sut.parse("a, b");

    assertOutput(parsed, "m.put(\"a\", given?.a)");
    assertOutput(parsed, "m.put(\"b\", given?.b)");
  }

  @Test
  public void testNestedSelect() {
    String parsed = sut.parse("a.b, c.d.e");

    assertOutput(parsed, "m.put(\"a.b\", given?.a?.b)");
    assertOutput(parsed, "m.put(\"c.d.e\", given?.c?.d?.e)");
  }

  @Test
  public void testAliasedSelect() {
    String parsed = sut.parse("a as theA, b.c as theC");

    assertOutput(parsed, "m.put(\"theA\", given?.a)");
    assertOutput(parsed, "m.put(\"theC\", given?.b?.c)");
  }

  @Test
  public void testArraySelect() {
    String parsed = sut.parse("a[0], b[1]");

    assertOutput(parsed, "m.put(\"a[0]\", given?.a[0])");
    assertOutput(parsed, "m.put(\"b[1]\", given?.b[1])");
  }

  @Test
  public void testAdditionOfLiterals() {
    String parsed = sut.parse("1 + 1 as two");

    assertOutput(parsed, "m.put(\"two\", 1+1)");
  }

  @Test
  public void testAdditionOfLiteralAndAccessor() {
    String parsed = sut.parse("1 + a.b as two");

    assertOutput(parsed, "m.put(\"two\", 1+given?.a?.b)");
  }

  @Test
  public void testMultiplicationOfLiterals() {
    String parsed = sut.parse("2 * 5 as ten");

    assertOutput(parsed, "m.put(\"ten\", 2*5)");
  }

  @Test
  public void testMultiplicationOfLiteralAndAccessor() {
    String parsed = sut.parse("2 * a.b as two");

    assertOutput(parsed, "m.put(\"two\", 2*given?.a?.b)");
  }

  @Test
  public void testStringLiteral() {
    String parsed = sut.parse("'Bob' as name");

    assertOutput(parsed, "m.put(\"name\", 'Bob')");
  }

  @Test
  public void testStringLiteralConcatenation() {
    String parsed = sut.parse("'Not ' + foo.serviceStatus as notServiceStatus");

    assertOutput(parsed, "m.put(\"notServiceStatus\", 'Not '+given?.foo?.serviceStatus)");
  }

  @Test
  public void testCombination() {
    String parsed = sut.parse("a, b.c, 2+2 as four, 'Bob' as name");

    assertOutput(parsed, "m.put(\"a\", given?.a)");
    assertOutput(parsed, "m.put(\"b.c\", given?.b?.c)");
    assertOutput(parsed, "m.put(\"four\", 2+2)");
    assertOutput(parsed, "m.put(\"name\", 'Bob')");
  }

  @Test
  public void testEmptyExpression() {
    String parsed = sut.parse("");

    assertOutput(
        parsed,
        "    Map<String, Object> project(Object given) {\n" + "       return given;\n" + "    }");
  }

  @Test
  public void testNullExpression() {
    String parsed = sut.parse(null);

    assertOutput(
        parsed,
        "    Map<String, Object> project(Object given) {\n" + "       return given;\n" + "    }");
  }

  @Test
  @ExpectedException(
      type = TranquilParserException.class,
      messageIs =
          "Failed to parse expression due to: [ no viable alternative at input '<EOF>' ] at position: 3 in line 1")
  public void willThrowIfTheExpressionIsInvalid() {
    sut.parse("a, ");
  }

  @Test
  @ExpectedException(
      type = TranquilParserException.class,
      messageIs =
          "Failed to parse expression due to: [ no viable alternative at input ',' ] at position: 7 in line 1, "
              + "[ no viable alternative at input '<EOF>' ] at position: 10 in line 1")
  public void willGatherAllFailedElementsInAnInvalidExpressionAndThrowThemTogether() {
    sut.parse("a, b as, c");
  }

  private void assertOutput(String parsed, String expectedExpression) {
    assertThat(parsed, containsString(expectedExpression));
  }
}
