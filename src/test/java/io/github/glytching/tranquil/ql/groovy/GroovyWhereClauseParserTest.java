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

import static io.github.glytching.tranquil.ql.groovy.GroovyWhereClauseListener.GROOVY_SCRIPT_TEMPLATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GroovyWhereClauseParserTest {

  private GroovyWhereClauseParser sut = new GroovyWhereClauseParser();

  @Test
  public void testSimpleOperators() {
    String script = sut.parse("a = 'this' and b < 1 and c <= 1 and d > 1 and e >= 1");

    assertOutput(
        script, "given?.a==\"this\" && given?.b<1 && given?.c<=1 && given?.d>1 && given?.e>=1");
  }

  @Test
  public void testMultipleWheresOnOneIdentifier() {
    String parsed = sut.parse("x >= 1.0 and x <= 5.5 and x != 2.6");

    assertOutput(parsed, "given?.x>=1.0 && given?.x<=5.5 && given?.x!=2.6");
  }

  @Test
  public void testIntegerValues() {
    String parsed = sut.parse("x = 1 and y in (10, 11) and z <= 20 and p > 30 and q != 40");

    assertOutput(
        parsed,
        "given?.x==1 "
            + "&& given?.y in [10,11] "
            + "&& given?.z<=20 "
            + "&& given?.p>30 "
            + "&& given?.q!=40");
  }

  @Test
  public void testSignedNumerics() {
    String parsed =
        sut.parse(
            "x = -10 "
                + "and y < -50 "
                + "and z in (-100, 100) "
                + "and p > -20 "
                + "and q <=-30 "
                + "and r >= -40");

    assertOutput(
        parsed,
        "given?.x==-10 "
            + "&& given?.y<-50 "
            + "&& given?.z in [-100,100] "
            + "&& given?.p>-20 "
            + "&& given?.q<=-30 "
            + "&& given?.r>=-40");
  }

  @Test
  public void testNullOperator() {
    String parsed = sut.parse("x is null and y is not null");

    assertOutput(parsed, "given?.x==null && !(given?.y==null)");
  }

  @Test
  public void testInOperator() {
    String parsed =
        sut.parse(
            "x in ('a', 'b') and y in (-10.1, -11.2) " + "and z not in (1, 2) and p in (101, 102)");

    assertOutput(
        parsed,
        "given?.x in [\"a\",\"b\"] "
            + "&& given?.y in [-10.1,-11.2] "
            + "&& !(given?.z in [1,2]) "
            + "&& given?.p in [101,102]");
  }

  @Test
  public void testLikeOperator() {
    String parsed =
        sut.parse(
            "x like 'truc%' "
                + "and y like '%tap%' "
                + "and z not like '%ruck' "
                + "and p.q like '%car%'");

    assertOutput(
        parsed,
        "given?.x=~\"truc%\" "
            + "&& given?.y=~\"%tap%\" "
            + "&& !(given?.z=~\"%ruck\") "
            + "&& given?.p?.q=~\"%car%\"");
  }

  @Test
  public void testSpecificArrayMember() {
    String parsed = sut.parse("x[0].y = 'foo'");

    assertOutput(parsed, "given?.x[0]?.y==\"foo\"");
  }

  @Test
  public void testAnyArrayMember() {
    String parsed = sut.parse("x[*].y = 'foo'");

    assertOutput(parsed, "given?.x?.findResult(false){ v -> v?.y==\"foo\" }");
  }

  @Test
  public void testSimpleOperatorsOnNestedFields() {
    String parsed = sut.parse("x.y = 'foo' and x.z < 10 and x.p <= 100 and q.r > 40 and q.s >= 50");

    assertOutput(
        parsed,
        "given?.x?.y==\"foo\" && given?.x?.z<10 && given?.x?.p<=100 && given?.q?.r>40 && given?.q?.s>=50");
  }

  @Test
  public void testOr() {
    String parsed = sut.parse("x > 0 or y = 'foo'");

    assertOutput(parsed, "given?.x>0 || given?.y==\"foo\"");
  }

  @Test
  public void testOrWithIn() {
    String parsed = sut.parse("x > 0 or y in ('foo', 'bar') or z not in (1, 2, 3)");

    assertOutput(parsed, "given?.x>0 || given?.y in [\"foo\",\"bar\"] || !(given?.z in [1,2,3])");
  }

  @Test
  public void testOrWithNull() {
    String parsed = sut.parse("x is null or y is not null");

    assertOutput(parsed, "given?.x==null || !(given?.y==null)");
  }

  @Test
  public void testOrWithLike() {
    String parsed = sut.parse("x like '%foo%' or y not like '%bar%'");

    assertOutput(parsed, "given?.x=~\"%foo%\" || !(given?.y=~\"%bar%\")");
  }

  @Test
  public void testOrNested() {
    String parsed = sut.parse("(x > 0 and y < 2) or z = 'foo'");

    assertOutput(parsed, "[given?.x>0 && given?.y<2] || given?.z==\"foo\"");
  }

  @Test
  public void testOrDeepNested() {
    String parsed = sut.parse("(x > 0 and (y < 2 or (z = 'foo' and p > 5)) and q = 4)");

    assertOutput(
        parsed, "[given?.x>0 && [given?.y<2 || [given?.z==\"foo\" && given?.p>5]] && given?.q==4]");
  }

  @Test
  public void testWithParentheses() {
    String parsed = sut.parse("(x = 'foo') and (y = 'bar')");

    assertOutput(parsed, "[given?.x==\"foo\"] && [given?.y==\"bar\"]");
  }

  @Test
  public void testEmptyExpression() {
    String parsed = sut.parse("");

    assertThat(parsed, is(GROOVY_SCRIPT_TEMPLATE));
  }

  @Test
  public void testNullExpression() {
    String parsed = sut.parse(null);

    assertThat(parsed, is(GROOVY_SCRIPT_TEMPLATE));
  }

  @Test
  @ExpectedException(
      type = TranquilParserException.class,
      messageIs =
          "Failed to parse expression due to: [ no viable alternative at input '<EOF>' ] at position: 3 in line 1")
  public void willThrowIfTheExpressionIsInvalid() {
    sut.parse("a= ");
  }

  @Test
  @ExpectedException(
      type = TranquilParserException.class,
      messageIs =
          "Failed to parse expression due to: [ no viable alternative at input 'and' ] at position: 19 in line 1, "
              + "[ no viable alternative at input '!=' ] at position: 23 in line 1")
  public void willGatherAllFailedElementsInAnInvalidExpressionAndThrowThemTogether() {
    sut.parse("a = 'this' " + "and b < " + "and !=");
  }

  private void assertOutput(String script, String expectedExpression) {
    assertThat(script, is(GROOVY_SCRIPT_TEMPLATE.replace("true", expectedExpression)));
  }
}
