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

import io.github.glytching.tranquil.antlr.SQLParser;
import io.github.glytching.tranquil.antlr.SQLParserBaseListener;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.logging.Logger;

import static java.util.logging.Level.FINEST;

/**
 * A {@link SQLParserBaseListener} which logs each ANTLR callback. This is useful when
 * creating/maintaining bespoke listeners but it is <b>very</b> verbose so it logs at the {@code
 * FINEST} level. Typically, this output will be muted but can be enabled by tweaking the logging
 * subsystem's config.
 */
public class TranquilLoggingListener extends SQLParserBaseListener {
  private static final Logger logger = Logger.getLogger(TranquilLoggingListener.class.getName());

  @Override
  public void enterAlter_table_statement(SQLParser.Alter_table_statementContext ctx) {
    super.enterAlter_table_statement(ctx);
    log("enterAlter_table_statement: {0}", ctx.start.getText());
  }

  @Override
  public void enterExplain_clause(SQLParser.Explain_clauseContext ctx) {
    super.enterExplain_clause(ctx);
    log("enterExplain_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitExplain_clause(SQLParser.Explain_clauseContext ctx) {
    super.exitExplain_clause(ctx);
    log("exitExplain_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterSession_statement(SQLParser.Session_statementContext ctx) {
    super.enterSession_statement(ctx);
    log("enterSession_statement: {0}", ctx.start.getText());
  }

  @Override
  public void exitSession_statement(SQLParser.Session_statementContext ctx) {
    super.exitSession_statement(ctx);
    log("exitSession_statement: {0}", ctx.start.getText());
  }

  @Override
  public void enterCreate_index_statement(SQLParser.Create_index_statementContext ctx) {
    super.enterCreate_index_statement(ctx);
    log("enterCreate_index_statement: {0}", ctx.start.getText());
  }

  @Override
  public void exitCreate_index_statement(SQLParser.Create_index_statementContext ctx) {
    super.exitCreate_index_statement(ctx);
    log("exitCreate_index_statement: {0}", ctx.start.getText());
  }

  @Override
  public void enterDrop_index_statement(SQLParser.Drop_index_statementContext ctx) {
    super.enterDrop_index_statement(ctx);
    log("enterDrop_index_statement: {0}", ctx.start.getText());
  }

  @Override
  public void exitDrop_index_statement(SQLParser.Drop_index_statementContext ctx) {
    super.exitDrop_index_statement(ctx);
    log("exitDrop_index_statement: {0}", ctx.start.getText());
  }

  @Override
  public void enterDatabase_definition(SQLParser.Database_definitionContext ctx) {
    super.enterDatabase_definition(ctx);
    log("enterDatabase_definition: {0}", ctx.start.getText());
  }

  @Override
  public void exitDatabase_definition(SQLParser.Database_definitionContext ctx) {
    super.exitDatabase_definition(ctx);
    log("exitDatabase_definition: {0}", ctx.start.getText());
  }

  @Override
  public void enterIf_not_exists(SQLParser.If_not_existsContext ctx) {
    super.enterIf_not_exists(ctx);
    log("enterIf_not_exists: {0}", ctx.start.getText());
  }

  @Override
  public void exitIf_not_exists(SQLParser.If_not_existsContext ctx) {
    super.exitIf_not_exists(ctx);
    log("exitIf_not_exists: {0}", ctx.start.getText());
  }

  @Override
  public void enterDrop_database_statement(SQLParser.Drop_database_statementContext ctx) {
    super.enterDrop_database_statement(ctx);
    log("enterDrop_database_statement: {0}", ctx.start.getText());
  }

  @Override
  public void exitDrop_database_statement(SQLParser.Drop_database_statementContext ctx) {
    super.exitDrop_database_statement(ctx);
    log("exitDrop_database_statement: {0}", ctx.start.getText());
  }

  @Override
  public void enterIf_exists(SQLParser.If_existsContext ctx) {
    super.enterIf_exists(ctx);
    log("enterIf_exists: {0}", ctx.start.getText());
  }

  @Override
  public void exitIf_exists(SQLParser.If_existsContext ctx) {
    super.exitIf_exists(ctx);
    log("exitIf_exists: {0}", ctx.start.getText());
  }

  @Override
  public void enterTruncate_table_statement(SQLParser.Truncate_table_statementContext ctx) {
    super.enterTruncate_table_statement(ctx);
    log("enterTruncate_table_statement: {0}", ctx.start.getText());
  }

  @Override
  public void exitTruncate_table_statement(SQLParser.Truncate_table_statementContext ctx) {
    super.exitTruncate_table_statement(ctx);
    log("exitTruncate_table_statement: {0}", ctx.start.getText());
  }

  @Override
  public void enterInterval_literal(SQLParser.Interval_literalContext ctx) {
    super.enterInterval_literal(ctx);
    log("enterInterval_literal: {0}", ctx.start.getText());
  }

  @Override
  public void exitInterval_literal(SQLParser.Interval_literalContext ctx) {
    super.exitInterval_literal(ctx);
    log("exitInterval_literal: {0}", ctx.start.getText());
  }

  @Override
  public void enterComplex_type(SQLParser.Complex_typeContext ctx) {
    super.enterComplex_type(ctx);
    log("enterComplex_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitComplex_type(SQLParser.Complex_typeContext ctx) {
    super.exitComplex_type(ctx);
    log("exitComplex_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterArray_type(SQLParser.Array_typeContext ctx) {
    super.enterArray_type(ctx);
    log("enterArray_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitArray_type(SQLParser.Array_typeContext ctx) {
    super.exitArray_type(ctx);
    log("exitArray_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterRecord_type(SQLParser.Record_typeContext ctx) {
    super.enterRecord_type(ctx);
    log("enterRecord_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitRecord_type(SQLParser.Record_typeContext ctx) {
    super.exitRecord_type(ctx);
    log("exitRecord_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterMap_type(SQLParser.Map_typeContext ctx) {
    super.enterMap_type(ctx);
    log("enterMap_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitMap_type(SQLParser.Map_typeContext ctx) {
    super.exitMap_type(ctx);
    log("exitMap_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterWindow_function(SQLParser.Window_functionContext ctx) {
    super.enterWindow_function(ctx);
    log("enterWindow_function: {0}", ctx.start.getText());
  }

  @Override
  public void exitWindow_function(SQLParser.Window_functionContext ctx) {
    super.exitWindow_function(ctx);
    log("exitWindow_function: {0}", ctx.start.getText());
  }

  @Override
  public void enterWindow_function_type(SQLParser.Window_function_typeContext ctx) {
    super.enterWindow_function_type(ctx);
    log("enterWindow_function_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitWindow_function_type(SQLParser.Window_function_typeContext ctx) {
    super.exitWindow_function_type(ctx);
    log("exitWindow_function_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterRank_function_type(SQLParser.Rank_function_typeContext ctx) {
    super.enterRank_function_type(ctx);
    log("enterRank_function_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitRank_function_type(SQLParser.Rank_function_typeContext ctx) {
    super.exitRank_function_type(ctx);
    log("exitRank_function_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterWindow_name_or_specification(SQLParser.Window_name_or_specificationContext ctx) {
    super.enterWindow_name_or_specification(ctx);
    log("enterWindow_name_or_specification: {0}", ctx.start.getText());
  }

  @Override
  public void exitWindow_name_or_specification(SQLParser.Window_name_or_specificationContext ctx) {
    super.exitWindow_name_or_specification(ctx);
    log("exitWindow_name_or_specification: {0}", ctx.start.getText());
  }

  @Override
  public void enterDatetime_value_expression(SQLParser.Datetime_value_expressionContext ctx) {
    super.enterDatetime_value_expression(ctx);
    log("enterDatetime_value_expression: {0}", ctx.start.getText());
  }

  @Override
  public void exitDatetime_value_expression(SQLParser.Datetime_value_expressionContext ctx) {
    super.exitDatetime_value_expression(ctx);
    log("exitDatetime_value_expression: {0}", ctx.start.getText());
  }

  @Override
  public void enterDatetime_term(SQLParser.Datetime_termContext ctx) {
    super.enterDatetime_term(ctx);
    log("enterDatetime_term: {0}", ctx.start.getText());
  }

  @Override
  public void exitDatetime_term(SQLParser.Datetime_termContext ctx) {
    super.exitDatetime_term(ctx);
    log("exitDatetime_term: {0}", ctx.start.getText());
  }

  @Override
  public void enterDatetime_factor(SQLParser.Datetime_factorContext ctx) {
    super.enterDatetime_factor(ctx);
    log("enterDatetime_factor: {0}", ctx.start.getText());
  }

  @Override
  public void exitDatetime_factor(SQLParser.Datetime_factorContext ctx) {
    super.exitDatetime_factor(ctx);
    log("exitDatetime_factor: {0}", ctx.start.getText());
  }

  @Override
  public void enterDatetime_primary(SQLParser.Datetime_primaryContext ctx) {
    super.enterDatetime_primary(ctx);
    log("enterDatetime_primary: {0}", ctx.start.getText());
  }

  @Override
  public void exitDatetime_primary(SQLParser.Datetime_primaryContext ctx) {
    super.exitDatetime_primary(ctx);
    log("exitDatetime_primary: {0}", ctx.start.getText());
  }

  @Override
  public void enterDatetime_value_function(SQLParser.Datetime_value_functionContext ctx) {
    super.enterDatetime_value_function(ctx);
    log("enterDatetime_value_function: {0}", ctx.start.getText());
  }

  @Override
  public void exitDatetime_value_function(SQLParser.Datetime_value_functionContext ctx) {
    super.exitDatetime_value_function(ctx);
    log("exitDatetime_value_function: {0}", ctx.start.getText());
  }

  @Override
  public void enterCurrent_date_value_function(SQLParser.Current_date_value_functionContext ctx) {
    super.enterCurrent_date_value_function(ctx);
    log("enterCurrent_date_value_function: {0}", ctx.start.getText());
  }

  @Override
  public void exitCurrent_date_value_function(SQLParser.Current_date_value_functionContext ctx) {
    super.exitCurrent_date_value_function(ctx);
    log("exitCurrent_date_value_function: {0}", ctx.start.getText());
  }

  @Override
  public void enterCurrent_time_value_function(SQLParser.Current_time_value_functionContext ctx) {
    super.enterCurrent_time_value_function(ctx);
    log("enterCurrent_time_value_function: {0}", ctx.start.getText());
  }

  @Override
  public void exitCurrent_time_value_function(SQLParser.Current_time_value_functionContext ctx) {
    super.exitCurrent_time_value_function(ctx);
    log("exitCurrent_time_value_function: {0}", ctx.start.getText());
  }

  @Override
  public void enterCurrent_timestamp_value_function(
      SQLParser.Current_timestamp_value_functionContext ctx) {
    super.enterCurrent_timestamp_value_function(ctx);
    log("enterCurrent_timestamp_value_function: {0}", ctx.start.getText());
  }

  @Override
  public void exitCurrent_timestamp_value_function(
      SQLParser.Current_timestamp_value_functionContext ctx) {
    super.exitCurrent_timestamp_value_function(ctx);
    log("exitCurrent_timestamp_value_function: {0}", ctx.start.getText());
  }

  @Override
  public void enterWindow_clause(SQLParser.Window_clauseContext ctx) {
    super.enterWindow_clause(ctx);
    log("enterWindow_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitWindow_clause(SQLParser.Window_clauseContext ctx) {
    super.exitWindow_clause(ctx);
    log("exitWindow_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterWindow_definition_list(SQLParser.Window_definition_listContext ctx) {
    super.enterWindow_definition_list(ctx);
    log("enterWindow_definition_list: {0}", ctx.start.getText());
  }

  @Override
  public void exitWindow_definition_list(SQLParser.Window_definition_listContext ctx) {
    super.exitWindow_definition_list(ctx);
    log("exitWindow_definition_list: {0}", ctx.start.getText());
  }

  @Override
  public void enterWindow_definition(SQLParser.Window_definitionContext ctx) {
    super.enterWindow_definition(ctx);
    log("enterWindow_definition: {0}", ctx.start.getText());
  }

  @Override
  public void exitWindow_definition(SQLParser.Window_definitionContext ctx) {
    super.exitWindow_definition(ctx);
    log("exitWindow_definition: {0}", ctx.start.getText());
  }

  @Override
  public void enterWindow_name(SQLParser.Window_nameContext ctx) {
    super.enterWindow_name(ctx);
    log("enterWindow_name: {0}", ctx.start.getText());
  }

  @Override
  public void exitWindow_name(SQLParser.Window_nameContext ctx) {
    super.exitWindow_name(ctx);
    log("exitWindow_name: {0}", ctx.start.getText());
  }

  @Override
  public void enterWindow_specification(SQLParser.Window_specificationContext ctx) {
    super.enterWindow_specification(ctx);
    log("enterWindow_specification: {0}", ctx.start.getText());
  }

  @Override
  public void exitWindow_specification(SQLParser.Window_specificationContext ctx) {
    super.exitWindow_specification(ctx);
    log("exitWindow_specification: {0}", ctx.start.getText());
  }

  @Override
  public void enterWindow_specification_details(SQLParser.Window_specification_detailsContext ctx) {
    super.enterWindow_specification_details(ctx);
    log("enterWindow_specification_details: {0}", ctx.start.getText());
  }

  @Override
  public void exitWindow_specification_details(SQLParser.Window_specification_detailsContext ctx) {
    super.exitWindow_specification_details(ctx);
    log("exitWindow_specification_details: {0}", ctx.start.getText());
  }

  @Override
  public void enterExisting_window_name(SQLParser.Existing_window_nameContext ctx) {
    super.enterExisting_window_name(ctx);
    log("enterExisting_window_name: {0}", ctx.start.getText());
  }

  @Override
  public void exitExisting_window_name(SQLParser.Existing_window_nameContext ctx) {
    super.exitExisting_window_name(ctx);
    log("exitExisting_window_name: {0}", ctx.start.getText());
  }

  @Override
  public void enterWindow_partition_clause(SQLParser.Window_partition_clauseContext ctx) {
    super.enterWindow_partition_clause(ctx);
    log("enterWindow_partition_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitWindow_partition_clause(SQLParser.Window_partition_clauseContext ctx) {
    super.exitWindow_partition_clause(ctx);
    log("exitWindow_partition_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterWindow_order_clause(SQLParser.Window_order_clauseContext ctx) {
    super.enterWindow_order_clause(ctx);
    log("enterWindow_order_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitWindow_order_clause(SQLParser.Window_order_clauseContext ctx) {
    super.exitWindow_order_clause(ctx);
    log("exitWindow_order_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterWindow_frame_clause(SQLParser.Window_frame_clauseContext ctx) {
    super.enterWindow_frame_clause(ctx);
    log("enterWindow_frame_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitWindow_frame_clause(SQLParser.Window_frame_clauseContext ctx) {
    super.exitWindow_frame_clause(ctx);
    log("exitWindow_frame_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterWindow_frame_units(SQLParser.Window_frame_unitsContext ctx) {
    super.enterWindow_frame_units(ctx);
    log("enterWindow_frame_units: {0}", ctx.start.getText());
  }

  @Override
  public void exitWindow_frame_units(SQLParser.Window_frame_unitsContext ctx) {
    super.exitWindow_frame_units(ctx);
    log("exitWindow_frame_units: {0}", ctx.start.getText());
  }

  @Override
  public void enterWindow_frame_extent(SQLParser.Window_frame_extentContext ctx) {
    super.enterWindow_frame_extent(ctx);
    log("enterWindow_frame_extent: {0}", ctx.start.getText());
  }

  @Override
  public void exitWindow_frame_extent(SQLParser.Window_frame_extentContext ctx) {
    super.exitWindow_frame_extent(ctx);
    log("exitWindow_frame_extent: {0}", ctx.start.getText());
  }

  @Override
  public void enterWindow_frame_start_bound(SQLParser.Window_frame_start_boundContext ctx) {
    super.enterWindow_frame_start_bound(ctx);
    log("enterWindow_frame_start_bound: {0}", ctx.start.getText());
  }

  @Override
  public void exitWindow_frame_start_bound(SQLParser.Window_frame_start_boundContext ctx) {
    super.exitWindow_frame_start_bound(ctx);
    log("exitWindow_frame_start_bound: {0}", ctx.start.getText());
  }

  @Override
  public void enterWindow_frame_between(SQLParser.Window_frame_betweenContext ctx) {
    super.enterWindow_frame_between(ctx);
    log("enterWindow_frame_between: {0}", ctx.start.getText());
  }

  @Override
  public void exitWindow_frame_between(SQLParser.Window_frame_betweenContext ctx) {
    super.exitWindow_frame_between(ctx);
    log("exitWindow_frame_between: {0}", ctx.start.getText());
  }

  @Override
  public void enterWindow_frame_end_bound(SQLParser.Window_frame_end_boundContext ctx) {
    super.enterWindow_frame_end_bound(ctx);
    log("enterWindow_frame_end_bound: {0}", ctx.start.getText());
  }

  @Override
  public void exitWindow_frame_end_bound(SQLParser.Window_frame_end_boundContext ctx) {
    super.exitWindow_frame_end_bound(ctx);
    log("exitWindow_frame_end_bound: {0}", ctx.start.getText());
  }

  @Override
  public void enterWindow_frame_exclusion(SQLParser.Window_frame_exclusionContext ctx) {
    super.enterWindow_frame_exclusion(ctx);
    log("enterWindow_frame_exclusion: {0}", ctx.start.getText());
  }

  @Override
  public void exitWindow_frame_exclusion(SQLParser.Window_frame_exclusionContext ctx) {
    super.exitWindow_frame_exclusion(ctx);
    log("exitWindow_frame_exclusion: {0}", ctx.start.getText());
  }

  @Override
  public void enterColumn_name(SQLParser.Column_nameContext ctx) {
    super.enterColumn_name(ctx);
    log("enterColumn_name: {0}", ctx.start.getText());
  }

  @Override
  public void exitColumn_name(SQLParser.Column_nameContext ctx) {
    super.exitColumn_name(ctx);
    log("exitColumn_name: {0}", ctx.start.getText());
  }

  @Override
  public void enterAsterisk(SQLParser.AsteriskContext ctx) {
    super.enterAsterisk(ctx);
    log("enterAsterisk: {0}", ctx.start.getText());
  }

  @Override
  public void exitAsterisk(SQLParser.AsteriskContext ctx) {
    super.exitAsterisk(ctx);
    log("exitAsterisk: {0}", ctx.start.getText());
  }

  @Override
  public void enterAlter_tablespace_statement(SQLParser.Alter_tablespace_statementContext ctx) {
    super.enterAlter_tablespace_statement(ctx);
    log("enterAlter_tablespace_statement: {0}", ctx.start.getText());
  }

  @Override
  public void exitAlter_tablespace_statement(SQLParser.Alter_tablespace_statementContext ctx) {
    super.exitAlter_tablespace_statement(ctx);
    log("exitAlter_tablespace_statement: {0}", ctx.start.getText());
  }

  @Override
  public void exitAlter_table_statement(SQLParser.Alter_table_statementContext ctx) {
    super.exitAlter_table_statement(ctx);
    log("exitAlter_table_statement: {0}", ctx.start.getText());
  }

  @Override
  public void enterPartition_column_value_list(SQLParser.Partition_column_value_listContext ctx) {
    super.enterPartition_column_value_list(ctx);
    log("enterPartition_column_value_list: {0}", ctx.start.getText());
  }

  @Override
  public void exitPartition_column_value_list(SQLParser.Partition_column_value_listContext ctx) {
    super.exitPartition_column_value_list(ctx);
    log("exitPartition_column_value_list: {0}", ctx.start.getText());
  }

  @Override
  public void enterPartition_column_value(SQLParser.Partition_column_valueContext ctx) {
    super.enterPartition_column_value(ctx);
    log("enterPartition_column_value: {0}", ctx.start.getText());
  }

  @Override
  public void exitPartition_column_value(SQLParser.Partition_column_valueContext ctx) {
    super.exitPartition_column_value(ctx);
    log("exitPartition_column_value: {0}", ctx.start.getText());
  }

  @Override
  public void enterProperty_list(SQLParser.Property_listContext ctx) {
    super.enterProperty_list(ctx);
    log("enterProperty_list: {0}", ctx.start.getText());
  }

  @Override
  public void exitProperty_list(SQLParser.Property_listContext ctx) {
    super.exitProperty_list(ctx);
    log("exitProperty_list: {0}", ctx.start.getText());
  }

  @Override
  public void enterProperty(SQLParser.PropertyContext ctx) {
    super.enterProperty(ctx);
    log("enterProperty: {0}", ctx.start.getText());
  }

  @Override
  public void exitProperty(SQLParser.PropertyContext ctx) {
    super.exitProperty(ctx);
    log("exitProperty: {0}", ctx.start.getText());
  }

  @Override
  public void enterProperty_key_list(SQLParser.Property_key_listContext ctx) {
    super.enterProperty_key_list(ctx);
    log("enterProperty_key_list: {0}", ctx.start.getText());
  }

  @Override
  public void exitProperty_key_list(SQLParser.Property_key_listContext ctx) {
    super.exitProperty_key_list(ctx);
    log("exitProperty_key_list: {0}", ctx.start.getText());
  }

  @Override
  public void enterProperty_key(SQLParser.Property_keyContext ctx) {
    super.enterProperty_key(ctx);
    log("enterProperty_key: {0}", ctx.start.getText());
  }

  @Override
  public void exitProperty_key(SQLParser.Property_keyContext ctx) {
    super.exitProperty_key(ctx);
    log("exitProperty_key: {0}", ctx.start.getText());
  }

  @Override
  public void enterEveryRule(ParserRuleContext ctx) {
    super.enterEveryRule(ctx);
    log("enterEveryRule: {0}", ctx.start.getText());
  }

  @Override
  public void exitEveryRule(ParserRuleContext ctx) {
    super.exitEveryRule(ctx);
    log("exitEveryRule: {0}", ctx.start.getText());
  }

  @Override
  public void enterField_type(SQLParser.Field_typeContext ctx) {
    super.enterField_type(ctx);
    log("enterField_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitField_type(SQLParser.Field_typeContext ctx) {
    super.exitField_type(ctx);
    log("exitField_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterQuery_expression(SQLParser.Query_expressionContext ctx) {
    super.enterQuery_expression(ctx);
    log("enterQuery_expression: {0}", ctx.start.getText());
  }

  @Override
  public void exitQuery_expression(SQLParser.Query_expressionContext ctx) {
    super.exitQuery_expression(ctx);
    log("exitQuery_expression: {0}", ctx.start.getText());
  }

  @Override
  public void enterScalar_subquery(SQLParser.Scalar_subqueryContext ctx) {
    super.enterScalar_subquery(ctx);
    log("enterScalar_subquery: {0}", ctx.start.getText());
  }

  @Override
  public void exitScalar_subquery(SQLParser.Scalar_subqueryContext ctx) {
    super.exitScalar_subquery(ctx);
    log("exitScalar_subquery: {0}", ctx.start.getText());
  }

  @Override
  public void enterPredicate(SQLParser.PredicateContext ctx) {
    super.enterPredicate(ctx);
    log("enterPredicate: {0}", ctx.start.getText());
  }

  @Override
  public void exitPredicate(SQLParser.PredicateContext ctx) {
    super.exitPredicate(ctx);
    log("exitPredicate: {0}", ctx.start.getText());
  }

  @Override
  public void enterAggregate_function(SQLParser.Aggregate_functionContext ctx) {
    super.enterAggregate_function(ctx);
    log("enterAggregate_function: {0}", ctx.start.getText());
  }

  @Override
  public void exitAggregate_function(SQLParser.Aggregate_functionContext ctx) {
    super.exitAggregate_function(ctx);
    log("exitAggregate_function: {0}", ctx.start.getText());
  }

  @Override
  public void enterTable_reference_list(SQLParser.Table_reference_listContext ctx) {
    super.enterTable_reference_list(ctx);
    log("enterTable_reference_list: {0}", ctx.start.getText());
  }

  @Override
  public void exitTable_reference_list(SQLParser.Table_reference_listContext ctx) {
    super.exitTable_reference_list(ctx);
    log("exitTable_reference_list: {0}", ctx.start.getText());
  }

  @Override
  public void enterRollup_list(SQLParser.Rollup_listContext ctx) {
    super.enterRollup_list(ctx);
    log("enterRollup_list: {0}", ctx.start.getText());
  }

  @Override
  public void exitRollup_list(SQLParser.Rollup_listContext ctx) {
    super.exitRollup_list(ctx);
    log("exitRollup_list: {0}", ctx.start.getText());
  }

  @Override
  public void enterPattern_matcher(SQLParser.Pattern_matcherContext ctx) {
    super.enterPattern_matcher(ctx);
    log("enterPattern_matcher: {0}", ctx.start.getText());
  }

  @Override
  public void exitPattern_matcher(SQLParser.Pattern_matcherContext ctx) {
    super.exitPattern_matcher(ctx);
    log("exitPattern_matcher: {0}", ctx.start.getText());
  }

  @Override
  public void enterOr_predicate(SQLParser.Or_predicateContext ctx) {
    super.enterOr_predicate(ctx);
    log("enterOr_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void exitOr_predicate(SQLParser.Or_predicateContext ctx) {
    super.exitOr_predicate(ctx);
    log("exitOr_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void enterCube_list(SQLParser.Cube_listContext ctx) {
    super.enterCube_list(ctx);
    log("enterCube_list: {0}", ctx.start.getText());
  }

  @Override
  public void exitCube_list(SQLParser.Cube_listContext ctx) {
    super.exitCube_list(ctx);
    log("exitCube_list: {0}", ctx.start.getText());
  }

  @Override
  public void enterNumeric_type(SQLParser.Numeric_typeContext ctx) {
    super.enterNumeric_type(ctx);
    log("enterNumeric_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitNumeric_type(SQLParser.Numeric_typeContext ctx) {
    super.exitNumeric_type(ctx);
    log("exitNumeric_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterSchema_statement(SQLParser.Schema_statementContext ctx) {
    super.enterSchema_statement(ctx);
    log("enterSchema_statement: {0}", ctx.start.getText());
  }

  @Override
  public void exitSchema_statement(SQLParser.Schema_statementContext ctx) {
    super.exitSchema_statement(ctx);
    log("exitSchema_statement: {0}", ctx.start.getText());
  }

  @Override
  public void enterRow_value_special_case(SQLParser.Row_value_special_caseContext ctx) {
    super.enterRow_value_special_case(ctx);
    log("enterRow_value_special_case: {0}", ctx.start.getText());
  }

  @Override
  public void exitRow_value_special_case(SQLParser.Row_value_special_caseContext ctx) {
    super.exitRow_value_special_case(ctx);
    log("exitRow_value_special_case: {0}", ctx.start.getText());
  }

  @Override
  public void enterBinary_type(SQLParser.Binary_typeContext ctx) {
    super.enterBinary_type(ctx);
    log("enterBinary_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitBinary_type(SQLParser.Binary_typeContext ctx) {
    super.exitBinary_type(ctx);
    log("exitBinary_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterExplicit_row_value_constructor(
      SQLParser.Explicit_row_value_constructorContext ctx) {
    super.enterExplicit_row_value_constructor(ctx);
    log("enterExplicit_row_value_constructor: {0}", ctx.start.getText());
  }

  @Override
  public void exitExplicit_row_value_constructor(
      SQLParser.Explicit_row_value_constructorContext ctx) {
    super.exitExplicit_row_value_constructor(ctx);
    log("exitExplicit_row_value_constructor: {0}", ctx.start.getText());
  }

  @Override
  public void enterTime_literal(SQLParser.Time_literalContext ctx) {
    super.enterTime_literal(ctx);
    log("enterTime_literal: {0}", ctx.start.getText());
  }

  @Override
  public void exitTime_literal(SQLParser.Time_literalContext ctx) {
    super.exitTime_literal(ctx);
    log("exitTime_literal: {0}", ctx.start.getText());
  }

  @Override
  public void enterRow_value_constructor_predicand(
      SQLParser.Row_value_constructor_predicandContext ctx) {
    super.enterRow_value_constructor_predicand(ctx);
    log("enterRow_value_constructor_predicand: {0}", ctx.start.getText());
  }

  @Override
  public void exitRow_value_constructor_predicand(
      SQLParser.Row_value_constructor_predicandContext ctx) {
    super.exitRow_value_constructor_predicand(ctx);
    log("exitRow_value_constructor_predicand: {0}", ctx.start.getText());
  }

  @Override
  public void enterBit_type(SQLParser.Bit_typeContext ctx) {
    super.enterBit_type(ctx);
    log("enterBit_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitBit_type(SQLParser.Bit_typeContext ctx) {
    super.exitBit_type(ctx);
    log("exitBit_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterPrecision_param(SQLParser.Precision_paramContext ctx) {
    super.enterPrecision_param(ctx);
    log("enterPrecision_param: {0}", ctx.start.getText());
  }

  @Override
  public void exitPrecision_param(SQLParser.Precision_paramContext ctx) {
    super.exitPrecision_param(ctx);
    log("exitPrecision_param: {0}", ctx.start.getText());
  }

  @Override
  public void enterDrop_table_statement(SQLParser.Drop_table_statementContext ctx) {
    super.enterDrop_table_statement(ctx);
    log("enterDrop_table_statement: {0}", ctx.start.getText());
  }

  @Override
  public void exitDrop_table_statement(SQLParser.Drop_table_statementContext ctx) {
    super.exitDrop_table_statement(ctx);
    log("exitDrop_table_statement: {0}", ctx.start.getText());
  }

  @Override
  public void enterLimit_clause(SQLParser.Limit_clauseContext ctx) {
    super.enterLimit_clause(ctx);
    log("enterLimit_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitLimit_clause(SQLParser.Limit_clauseContext ctx) {
    super.exitLimit_clause(ctx);
    log("exitLimit_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterFrom_clause(SQLParser.From_clauseContext ctx) {
    super.enterFrom_clause(ctx);
    log("enterFrom_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitFrom_clause(SQLParser.From_clauseContext ctx) {
    super.exitFrom_clause(ctx);
    log("exitFrom_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterIn_predicate_value(SQLParser.In_predicate_valueContext ctx) {
    super.enterIn_predicate_value(ctx);
    log("enterIn_predicate_value: {0}", ctx.start.getText());
  }

  @Override
  public void exitIn_predicate_value(SQLParser.In_predicate_valueContext ctx) {
    super.exitIn_predicate_value(ctx);
    log("exitIn_predicate_value: {0}", ctx.start.getText());
  }

  @Override
  public void enterIndividual_hash_partition(SQLParser.Individual_hash_partitionContext ctx) {
    super.enterIndividual_hash_partition(ctx);
    log("enterIndividual_hash_partition: {0}", ctx.start.getText());
  }

  @Override
  public void exitIndividual_hash_partition(SQLParser.Individual_hash_partitionContext ctx) {
    super.exitIndividual_hash_partition(ctx);
    log("exitIndividual_hash_partition: {0}", ctx.start.getText());
  }

  @Override
  public void enterColumn_partitions(SQLParser.Column_partitionsContext ctx) {
    super.enterColumn_partitions(ctx);
    log("enterColumn_partitions: {0}", ctx.start.getText());
  }

  @Override
  public void exitColumn_partitions(SQLParser.Column_partitionsContext ctx) {
    super.exitColumn_partitions(ctx);
    log("exitColumn_partitions: {0}", ctx.start.getText());
  }

  @Override
  public void enterBoolean_test(SQLParser.Boolean_testContext ctx) {
    super.enterBoolean_test(ctx);
    log("enterBoolean_test: {0}", ctx.start.getText());
  }

  @Override
  public void exitBoolean_test(SQLParser.Boolean_testContext ctx) {
    super.exitBoolean_test(ctx);
    log("exitBoolean_test: {0}", ctx.start.getText());
  }

  @Override
  public void enterExists_predicate(SQLParser.Exists_predicateContext ctx) {
    super.enterExists_predicate(ctx);
    log("enterExists_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void exitExists_predicate(SQLParser.Exists_predicateContext ctx) {
    super.exitExists_predicate(ctx);
    log("exitExists_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void enterCharacter_factor(SQLParser.Character_factorContext ctx) {
    super.enterCharacter_factor(ctx);
    log("enterCharacter_factor: {0}", ctx.start.getText());
  }

  @Override
  public void exitCharacter_factor(SQLParser.Character_factorContext ctx) {
    super.exitCharacter_factor(ctx);
    log("exitCharacter_factor: {0}", ctx.start.getText());
  }

  @Override
  public void enterNamed_columns_join(SQLParser.Named_columns_joinContext ctx) {
    super.enterNamed_columns_join(ctx);
    log("enterNamed_columns_join: {0}", ctx.start.getText());
  }

  @Override
  public void exitNamed_columns_join(SQLParser.Named_columns_joinContext ctx) {
    super.exitNamed_columns_join(ctx);
    log("exitNamed_columns_join: {0}", ctx.start.getText());
  }

  @Override
  public void enterPattern_matching_predicate(SQLParser.Pattern_matching_predicateContext ctx) {
    super.enterPattern_matching_predicate(ctx);
    log("enterPattern_matching_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void exitPattern_matching_predicate(SQLParser.Pattern_matching_predicateContext ctx) {
    super.exitPattern_matching_predicate(ctx);
    log("exitPattern_matching_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void enterUnsigned_value_specification(SQLParser.Unsigned_value_specificationContext ctx) {
    super.enterUnsigned_value_specification(ctx);
    log("enterUnsigned_value_specification: {0}", ctx.start.getText());
  }

  @Override
  public void exitUnsigned_value_specification(SQLParser.Unsigned_value_specificationContext ctx) {
    super.exitUnsigned_value_specification(ctx);
    log("exitUnsigned_value_specification: {0}", ctx.start.getText());
  }

  @Override
  public void enterParam(SQLParser.ParamContext ctx) {
    super.enterParam(ctx);
    log("enterParam: {0}", ctx.start.getText());
  }

  @Override
  public void exitParam(SQLParser.ParamContext ctx) {
    super.exitParam(ctx);
    log("exitParam: {0}", ctx.start.getText());
  }

  @Override
  public void enterSimple_table(SQLParser.Simple_tableContext ctx) {
    super.enterSimple_table(ctx);
    log("enterSimple_table: {0}", ctx.start.getText());
  }

  @Override
  public void exitSimple_table(SQLParser.Simple_tableContext ctx) {
    super.exitSimple_table(ctx);
    log("exitSimple_table: {0}", ctx.start.getText());
  }

  @Override
  public void enterParam_clause(SQLParser.Param_clauseContext ctx) {
    super.enterParam_clause(ctx);
    log("enterParam_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitParam_clause(SQLParser.Param_clauseContext ctx) {
    super.exitParam_clause(ctx);
    log("exitParam_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterDerived_table(SQLParser.Derived_tableContext ctx) {
    super.enterDerived_table(ctx);
    log("enterDerived_table: {0}", ctx.start.getText());
  }

  @Override
  public void exitDerived_table(SQLParser.Derived_tableContext ctx) {
    super.exitDerived_table(ctx);
    log("exitDerived_table: {0}", ctx.start.getText());
  }

  @Override
  public void enterHaving_clause(SQLParser.Having_clauseContext ctx) {
    super.enterHaving_clause(ctx);
    log("enterHaving_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitHaving_clause(SQLParser.Having_clauseContext ctx) {
    super.exitHaving_clause(ctx);
    log("exitHaving_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterCharacter_string_type(SQLParser.Character_string_typeContext ctx) {
    super.enterCharacter_string_type(ctx);
    log("enterCharacter_string_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitCharacter_string_type(SQLParser.Character_string_typeContext ctx) {
    super.exitCharacter_string_type(ctx);
    log("exitCharacter_string_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterSet_function_type(SQLParser.Set_function_typeContext ctx) {
    super.enterSet_function_type(ctx);
    log("enterSet_function_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitSet_function_type(SQLParser.Set_function_typeContext ctx) {
    super.exitSet_function_type(ctx);
    log("exitSet_function_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterParenthesized_boolean_value_expression(
      SQLParser.Parenthesized_boolean_value_expressionContext ctx) {
    super.enterParenthesized_boolean_value_expression(ctx);
    log("enterParenthesized_boolean_value_expression: {0}", ctx.start.getText());
  }

  @Override
  public void exitParenthesized_boolean_value_expression(
      SQLParser.Parenthesized_boolean_value_expressionContext ctx) {
    super.exitParenthesized_boolean_value_expression(ctx);
    log("exitParenthesized_boolean_value_expression: {0}", ctx.start.getText());
  }

  @Override
  public void enterExtended_datetime_field(SQLParser.Extended_datetime_fieldContext ctx) {
    super.enterExtended_datetime_field(ctx);
    log("enterExtended_datetime_field: {0}", ctx.start.getText());
  }

  @Override
  public void exitExtended_datetime_field(SQLParser.Extended_datetime_fieldContext ctx) {
    super.exitExtended_datetime_field(ctx);
    log("exitExtended_datetime_field: {0}", ctx.start.getText());
  }

  @Override
  public void enterComp_op(SQLParser.Comp_opContext ctx) {
    super.enterComp_op(ctx);
    log("enterComp_op: {0}", ctx.start.getText());
  }

  @Override
  public void exitComp_op(SQLParser.Comp_opContext ctx) {
    super.exitComp_op(ctx);
    log("exitComp_op: {0}", ctx.start.getText());
  }

  @Override
  public void enterJoined_table(SQLParser.Joined_tableContext ctx) {
    super.enterJoined_table(ctx);
    log("enterJoined_table: {0}", ctx.start.getText());
  }

  @Override
  public void exitJoined_table(SQLParser.Joined_tableContext ctx) {
    super.exitJoined_table(ctx);
    log("exitJoined_table: {0}", ctx.start.getText());
  }

  @Override
  public void enterFactor(SQLParser.FactorContext ctx) {
    super.enterFactor(ctx);
    log("enterFactor: {0}", ctx.start.getText());
  }

  @Override
  public void exitFactor(SQLParser.FactorContext ctx) {
    super.exitFactor(ctx);
    log("exitFactor: {0}", ctx.start.getText());
  }

  @Override
  public void enterAny_array_element(SQLParser.Any_array_elementContext ctx) {
    super.enterAny_array_element(ctx);
    log("enterAny_array_element: {0}", ctx.start.getText());
  }

  @Override
  public void enterArray(SQLParser.ArrayContext ctx) {
    super.enterArray(ctx);
    log("enterArray: {0}", ctx.start.getText());
  }

  @Override
  public void enterArray_accessor(SQLParser.Array_accessorContext ctx) {
    super.enterArray_accessor(ctx);
    log("enterArray_accessor: {0}", ctx.start.getText());
  }

  @Override
  public void enterArray_declaration(SQLParser.Array_declarationContext ctx) {
    super.enterArray_declaration(ctx);
    log("enterArray_declaration: {0}", ctx.start.getText());
  }

  @Override
  public void exitAny_array_element(SQLParser.Any_array_elementContext ctx) {
    super.exitAny_array_element(ctx);
    log("exitAny_array_element: {0}", ctx.stop.getText());
  }

  @Override
  public void exitArray(SQLParser.ArrayContext ctx) {
    super.exitArray(ctx);
    log("exitArray: {0}", ctx.stop.getText());
  }

  @Override
  public void exitArray_accessor(SQLParser.Array_accessorContext ctx) {
    super.exitArray_accessor(ctx);
    log("exitArray_accessor: {0}", ctx.stop.getText());
  }

  @Override
  public void exitArray_declaration(SQLParser.Array_declarationContext ctx) {
    super.exitArray_declaration(ctx);
    log("exitArray_declaration: {0}", ctx.stop.getText());
  }

  @Override
  public void enterCase_expression(SQLParser.Case_expressionContext ctx) {
    super.enterCase_expression(ctx);
    log("enterCase_expression: {0}", ctx.start.getText());
  }

  @Override
  public void exitCase_expression(SQLParser.Case_expressionContext ctx) {
    super.exitCase_expression(ctx);
    log("exitCase_expression: {0}", ctx.start.getText());
  }

  @Override
  public void enterSelect_list(SQLParser.Select_listContext ctx) {
    super.enterSelect_list(ctx);
    log("enterSelect_list: {0}", ctx.start.getText());
  }

  @Override
  public void exitSelect_list(SQLParser.Select_listContext ctx) {
    super.exitSelect_list(ctx);
    log("exitSelect_list: {0}", ctx.start.getText());
  }

  @Override
  public void enterCharacter_value_expression(SQLParser.Character_value_expressionContext ctx) {
    super.enterCharacter_value_expression(ctx);
    log("enterCharacter_value_expression: {0}", ctx.start.getText());
  }

  @Override
  public void exitCharacter_value_expression(SQLParser.Character_value_expressionContext ctx) {
    super.exitCharacter_value_expression(ctx);
    log("exitCharacter_value_expression: {0}", ctx.start.getText());
  }

  @Override
  public void enterSort_specifier_list(SQLParser.Sort_specifier_listContext ctx) {
    super.enterSort_specifier_list(ctx);
    log("enterSort_specifier_list: {0}", ctx.start.getText());
  }

  @Override
  public void exitSort_specifier_list(SQLParser.Sort_specifier_listContext ctx) {
    super.exitSort_specifier_list(ctx);
    log("exitSort_specifier_list: {0}", ctx.start.getText());
  }

  @Override
  public void enterGeneral_literal(SQLParser.General_literalContext ctx) {
    super.enterGeneral_literal(ctx);
    log("enterGeneral_literal: {0}", ctx.start.getText());
  }

  @Override
  public void exitGeneral_literal(SQLParser.General_literalContext ctx) {
    super.exitGeneral_literal(ctx);
    log("exitGeneral_literal: {0}", ctx.start.getText());
  }

  @Override
  public void enterOrdinary_grouping_set(SQLParser.Ordinary_grouping_setContext ctx) {
    super.enterOrdinary_grouping_set(ctx);
    log("enterOrdinary_grouping_set: {0}", ctx.start.getText());
  }

  @Override
  public void exitOrdinary_grouping_set(SQLParser.Ordinary_grouping_setContext ctx) {
    super.exitOrdinary_grouping_set(ctx);
    log("exitOrdinary_grouping_set: {0}", ctx.start.getText());
  }

  @Override
  public void enterTime_zone_field(SQLParser.Time_zone_fieldContext ctx) {
    super.enterTime_zone_field(ctx);
    log("enterTime_zone_field: {0}", ctx.start.getText());
  }

  @Override
  public void exitTime_zone_field(SQLParser.Time_zone_fieldContext ctx) {
    super.exitTime_zone_field(ctx);
    log("exitTime_zone_field: {0}", ctx.start.getText());
  }

  @Override
  public void enterCast_target(SQLParser.Cast_targetContext ctx) {
    super.enterCast_target(ctx);
    log("enterCast_target: {0}", ctx.start.getText());
  }

  @Override
  public void exitCast_target(SQLParser.Cast_targetContext ctx) {
    super.exitCast_target(ctx);
    log("exitCast_target: {0}", ctx.start.getText());
  }

  @Override
  public void enterIndividual_hash_partitions(SQLParser.Individual_hash_partitionsContext ctx) {
    super.enterIndividual_hash_partitions(ctx);
    log("enterIndividual_hash_partitions: {0}", ctx.start.getText());
  }

  @Override
  public void exitIndividual_hash_partitions(SQLParser.Individual_hash_partitionsContext ctx) {
    super.exitIndividual_hash_partitions(ctx);
    log("exitIndividual_hash_partitions: {0}", ctx.start.getText());
  }

  @Override
  public void enterColumn_reference_list(SQLParser.Column_reference_listContext ctx) {
    super.enterColumn_reference_list(ctx);
    log("enterColumn_reference_list: {0}", ctx.start.getText());
  }

  @Override
  public void exitColumn_reference_list(SQLParser.Column_reference_listContext ctx) {
    super.exitColumn_reference_list(ctx);
    log("exitColumn_reference_list: {0}", ctx.start.getText());
  }

  @Override
  public void enterData_statement(SQLParser.Data_statementContext ctx) {
    super.enterData_statement(ctx);
    log("enterData_statement: {0}", ctx.start.getText());
  }

  @Override
  public void exitData_statement(SQLParser.Data_statementContext ctx) {
    super.exitData_statement(ctx);
    log("exitData_statement: {0}", ctx.start.getText());
  }

  @Override
  public void enterTable_expression(SQLParser.Table_expressionContext ctx) {
    super.enterTable_expression(ctx);
    log("enterTable_expression: {0}", ctx.start.getText());
  }

  @Override
  public void exitTable_expression(SQLParser.Table_expressionContext ctx) {
    super.exitTable_expression(ctx);
    log("exitTable_expression: {0}", ctx.start.getText());
  }

  @Override
  public void enterString_value_function(SQLParser.String_value_functionContext ctx) {
    super.enterString_value_function(ctx);
    log("enterString_value_function: {0}", ctx.start.getText());
  }

  @Override
  public void exitString_value_function(SQLParser.String_value_functionContext ctx) {
    super.exitString_value_function(ctx);
    log("exitString_value_function: {0}", ctx.start.getText());
  }

  @Override
  public void enterDerived_column(SQLParser.Derived_columnContext ctx) {
    super.enterDerived_column(ctx);
    log("enterDerived_column: {0}", ctx.start.getText());
  }

  @Override
  public void exitDerived_column(SQLParser.Derived_columnContext ctx) {
    super.exitDerived_column(ctx);
    log("exitDerived_column: {0}", ctx.start.getText());
  }

  @Override
  public void enterQuery_term(SQLParser.Query_termContext ctx) {
    super.enterQuery_term(ctx);
    log("enterQuery_term: {0}", ctx.start.getText());
  }

  @Override
  public void exitQuery_term(SQLParser.Query_termContext ctx) {
    super.exitQuery_term(ctx);
    log("exitQuery_term: {0}", ctx.start.getText());
  }

  @Override
  public void enterInsert_statement(SQLParser.Insert_statementContext ctx) {
    super.enterInsert_statement(ctx);
    log("enterInsert_statement: {0}", ctx.start.getText());
  }

  @Override
  public void exitInsert_statement(SQLParser.Insert_statementContext ctx) {
    super.exitInsert_statement(ctx);
    log("exitInsert_statement: {0}", ctx.start.getText());
  }

  @Override
  public void enterJoin_type(SQLParser.Join_typeContext ctx) {
    super.enterJoin_type(ctx);
    log("enterJoin_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitJoin_type(SQLParser.Join_typeContext ctx) {
    super.exitJoin_type(ctx);
    log("exitJoin_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterJoin_specification(SQLParser.Join_specificationContext ctx) {
    super.enterJoin_specification(ctx);
    log("enterJoin_specification: {0}", ctx.start.getText());
  }

  @Override
  public void exitJoin_specification(SQLParser.Join_specificationContext ctx) {
    super.exitJoin_specification(ctx);
    log("exitJoin_specification: {0}", ctx.start.getText());
  }

  @Override
  public void enterData_type(SQLParser.Data_typeContext ctx) {
    super.enterData_type(ctx);
    log("enterData_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitData_type(SQLParser.Data_typeContext ctx) {
    super.exitData_type(ctx);
    log("exitData_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterGrouping_element_list(SQLParser.Grouping_element_listContext ctx) {
    super.enterGrouping_element_list(ctx);
    log("enterGrouping_element_list: {0}", ctx.start.getText());
  }

  @Override
  public void exitGrouping_element_list(SQLParser.Grouping_element_listContext ctx) {
    super.exitGrouping_element_list(ctx);
    log("exitGrouping_element_list: {0}", ctx.start.getText());
  }

  @Override
  public void enterCast_operand(SQLParser.Cast_operandContext ctx) {
    super.enterCast_operand(ctx);
    log("enterCast_operand: {0}", ctx.start.getText());
  }

  @Override
  public void exitCast_operand(SQLParser.Cast_operandContext ctx) {
    super.exitCast_operand(ctx);
    log("exitCast_operand: {0}", ctx.start.getText());
  }

  @Override
  public void enterFunction_name(SQLParser.Function_nameContext ctx) {
    super.enterFunction_name(ctx);
    log("enterFunction_name: {0}", ctx.start.getText());
  }

  @Override
  public void exitFunction_name(SQLParser.Function_nameContext ctx) {
    super.exitFunction_name(ctx);
    log("exitFunction_name: {0}", ctx.start.getText());
  }

  @Override
  public void enterQuery_expression_body(SQLParser.Query_expression_bodyContext ctx) {
    super.enterQuery_expression_body(ctx);
    log("enterQuery_expression_body: {0}", ctx.start.getText());
  }

  @Override
  public void exitQuery_expression_body(SQLParser.Query_expression_bodyContext ctx) {
    super.exitQuery_expression_body(ctx);
    log("exitQuery_expression_body: {0}", ctx.start.getText());
  }

  @Override
  public void enterDatetime_literal(SQLParser.Datetime_literalContext ctx) {
    super.enterDatetime_literal(ctx);
    log("enterDatetime_literal: {0}", ctx.start.getText());
  }

  @Override
  public void exitDatetime_literal(SQLParser.Datetime_literalContext ctx) {
    super.exitDatetime_literal(ctx);
    log("exitDatetime_literal: {0}", ctx.start.getText());
  }

  @Override
  public void enterBoolean_factor(SQLParser.Boolean_factorContext ctx) {
    super.enterBoolean_factor(ctx);
    log("enterBoolean_factor: {0}", ctx.start.getText());
  }

  @Override
  public void exitBoolean_factor(SQLParser.Boolean_factorContext ctx) {
    super.exitBoolean_factor(ctx);
    log("exitBoolean_factor: {0}", ctx.start.getText());
  }

  @Override
  public void enterQuery_primary(SQLParser.Query_primaryContext ctx) {
    super.enterQuery_primary(ctx);
    log("enterQuery_primary: {0}", ctx.start.getText());
  }

  @Override
  public void exitQuery_primary(SQLParser.Query_primaryContext ctx) {
    super.exitQuery_primary(ctx);
    log("exitQuery_primary: {0}", ctx.start.getText());
  }

  @Override
  public void enterNumeric_value_expression(SQLParser.Numeric_value_expressionContext ctx) {
    super.enterNumeric_value_expression(ctx);
    log("enterNumeric_value_expression: {0}", ctx.start.getText());
  }

  @Override
  public void exitNumeric_value_expression(SQLParser.Numeric_value_expressionContext ctx) {
    super.exitNumeric_value_expression(ctx);
    log("exitNumeric_value_expression: {0}", ctx.start.getText());
  }

  @Override
  public void enterJoined_table_primary(SQLParser.Joined_table_primaryContext ctx) {
    super.enterJoined_table_primary(ctx);
    log("enterJoined_table_primary: {0}", ctx.start.getText());
  }

  @Override
  public void exitJoined_table_primary(SQLParser.Joined_table_primaryContext ctx) {
    super.exitJoined_table_primary(ctx);
    log("exitJoined_table_primary: {0}", ctx.start.getText());
  }

  @Override
  public void enterRoutine_invocation(SQLParser.Routine_invocationContext ctx) {
    super.enterRoutine_invocation(ctx);
    log("enterRoutine_invocation: {0}", ctx.start.getText());
  }

  @Override
  public void exitRoutine_invocation(SQLParser.Routine_invocationContext ctx) {
    super.exitRoutine_invocation(ctx);
    log("exitRoutine_invocation: {0}", ctx.start.getText());
  }

  @Override
  public void enterNon_join_query_expression(SQLParser.Non_join_query_expressionContext ctx) {
    super.enterNon_join_query_expression(ctx);
    log("enterNon_join_query_expression: {0}", ctx.start.getText());
  }

  @Override
  public void exitNon_join_query_expression(SQLParser.Non_join_query_expressionContext ctx) {
    super.exitNon_join_query_expression(ctx);
    log("exitNon_join_query_expression: {0}", ctx.start.getText());
  }

  @Override
  public void enterColumn_name_list(SQLParser.Column_name_listContext ctx) {
    super.enterColumn_name_list(ctx);
    log("enterColumn_name_list: {0}", ctx.start.getText());
  }

  @Override
  public void exitColumn_name_list(SQLParser.Column_name_listContext ctx) {
    super.exitColumn_name_list(ctx);
    log("exitColumn_name_list: {0}", ctx.start.getText());
  }

  @Override
  public void enterSign(SQLParser.SignContext ctx) {
    super.enterSign(ctx);
    log("enterSign: {0}", ctx.start.getText());
  }

  @Override
  public void exitSign(SQLParser.SignContext ctx) {
    super.exitSign(ctx);
    log("exitSign: {0}", ctx.start.getText());
  }

  @Override
  public void enterValue_expression_primary(SQLParser.Value_expression_primaryContext ctx) {
    super.enterValue_expression_primary(ctx);
    log("enterValue_expression_primary: {0}", ctx.start.getText());
  }

  @Override
  public void exitValue_expression_primary(SQLParser.Value_expression_primaryContext ctx) {
    super.exitValue_expression_primary(ctx);
    log("exitValue_expression_primary: {0}", ctx.start.getText());
  }

  @Override
  public void enterRange_partitions(SQLParser.Range_partitionsContext ctx) {
    super.enterRange_partitions(ctx);
    log("enterRange_partitions: {0}", ctx.start.getText());
  }

  @Override
  public void exitRange_partitions(SQLParser.Range_partitionsContext ctx) {
    super.exitRange_partitions(ctx);
    log("exitRange_partitions: {0}", ctx.start.getText());
  }

  @Override
  public void enterComparison_predicate(SQLParser.Comparison_predicateContext ctx) {
    super.enterComparison_predicate(ctx);
    log("enterComparison_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void exitComparison_predicate(SQLParser.Comparison_predicateContext ctx) {
    super.exitComparison_predicate(ctx);
    log("exitComparison_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void enterCase_specification(SQLParser.Case_specificationContext ctx) {
    super.enterCase_specification(ctx);
    log("enterCase_specification: {0}", ctx.start.getText());
  }

  @Override
  public void exitCase_specification(SQLParser.Case_specificationContext ctx) {
    super.exitCase_specification(ctx);
    log("exitCase_specification: {0}", ctx.start.getText());
  }

  @Override
  public void enterBinary_large_object_string_type(
      SQLParser.Binary_large_object_string_typeContext ctx) {
    super.enterBinary_large_object_string_type(ctx);
    log("enterBinary_large_object_string_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitBinary_large_object_string_type(
      SQLParser.Binary_large_object_string_typeContext ctx) {
    super.exitBinary_large_object_string_type(ctx);
    log("exitBinary_large_object_string_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterIn_predicate(SQLParser.In_predicateContext ctx) {
    super.enterIn_predicate(ctx);
    log("enterIn_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void exitIn_predicate(SQLParser.In_predicateContext ctx) {
    super.exitIn_predicate(ctx);
    log("exitIn_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void enterOuter_join_type(SQLParser.Outer_join_typeContext ctx) {
    super.enterOuter_join_type(ctx);
    log("enterOuter_join_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitOuter_join_type(SQLParser.Outer_join_typeContext ctx) {
    super.exitOuter_join_type(ctx);
    log("exitOuter_join_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterString_value_expression(SQLParser.String_value_expressionContext ctx) {
    super.enterString_value_expression(ctx);
    log("enterString_value_expression: {0}", ctx.start.getText());
  }

  @Override
  public void exitString_value_expression(SQLParser.String_value_expressionContext ctx) {
    super.exitString_value_expression(ctx);
    log("exitString_value_expression: {0}", ctx.start.getText());
  }

  @Override
  public void enterIdentifier(SQLParser.IdentifierContext ctx) {
    super.enterIdentifier(ctx);
    log("enterIdentifier: {0}", ctx.start.getText());
  }

  @Override
  public void exitIdentifier(SQLParser.IdentifierContext ctx) {
    super.exitIdentifier(ctx);
    log("exitIdentifier: {0}", ctx.start.getText());
  }

  @Override
  public void enterResult(SQLParser.ResultContext ctx) {
    super.enterResult(ctx);
    log("enterResult: {0}", ctx.start.getText());
  }

  @Override
  public void exitResult(SQLParser.ResultContext ctx) {
    super.exitResult(ctx);
    log("exitResult: {0}", ctx.start.getText());
  }

  @Override
  public void enterBoolean_literal(SQLParser.Boolean_literalContext ctx) {
    super.enterBoolean_literal(ctx);
    log("enterBoolean_literal: {0}", ctx.start.getText());
  }

  @Override
  public void exitBoolean_literal(SQLParser.Boolean_literalContext ctx) {
    super.exitBoolean_literal(ctx);
    log("exitBoolean_literal: {0}", ctx.start.getText());
  }

  @Override
  public void enterCommon_value_expression(SQLParser.Common_value_expressionContext ctx) {
    super.enterCommon_value_expression(ctx);
    log("enterCommon_value_expression: {0}", ctx.start.getText());
  }

  @Override
  public void exitCommon_value_expression(SQLParser.Common_value_expressionContext ctx) {
    super.exitCommon_value_expression(ctx);
    log("exitCommon_value_expression: {0}", ctx.start.getText());
  }

  @Override
  public void enterBoolean_type(SQLParser.Boolean_typeContext ctx) {
    super.enterBoolean_type(ctx);
    log("enterBoolean_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitBoolean_type(SQLParser.Boolean_typeContext ctx) {
    super.exitBoolean_type(ctx);
    log("exitBoolean_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterTable_reference(SQLParser.Table_referenceContext ctx) {
    super.enterTable_reference(ctx);
    log("enterTable_reference: {0}", ctx.start.getText());
  }

  @Override
  public void exitTable_reference(SQLParser.Table_referenceContext ctx) {
    super.exitTable_reference(ctx);
    log("exitTable_reference: {0}", ctx.start.getText());
  }

  @Override
  public void enterTable_or_query_name(SQLParser.Table_or_query_nameContext ctx) {
    super.enterTable_or_query_name(ctx);
    log("enterTable_or_query_name: {0}", ctx.start.getText());
  }

  @Override
  public void exitTable_or_query_name(SQLParser.Table_or_query_nameContext ctx) {
    super.exitTable_or_query_name(ctx);
    log("exitTable_or_query_name: {0}", ctx.start.getText());
  }

  @Override
  public void enterCase_abbreviation(SQLParser.Case_abbreviationContext ctx) {
    super.enterCase_abbreviation(ctx);
    log("enterCase_abbreviation: {0}", ctx.start.getText());
  }

  @Override
  public void exitCase_abbreviation(SQLParser.Case_abbreviationContext ctx) {
    super.exitCase_abbreviation(ctx);
    log("exitCase_abbreviation: {0}", ctx.start.getText());
  }

  @Override
  public void enterEmpty_grouping_set(SQLParser.Empty_grouping_setContext ctx) {
    super.enterEmpty_grouping_set(ctx);
    log("enterEmpty_grouping_set: {0}", ctx.start.getText());
  }

  @Override
  public void exitEmpty_grouping_set(SQLParser.Empty_grouping_setContext ctx) {
    super.exitEmpty_grouping_set(ctx);
    log("exitEmpty_grouping_set: {0}", ctx.start.getText());
  }

  @Override
  public void enterNull_predicate(SQLParser.Null_predicateContext ctx) {
    super.enterNull_predicate(ctx);
    log("enterNull_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void exitNull_predicate(SQLParser.Null_predicateContext ctx) {
    super.exitNull_predicate(ctx);
    log("exitNull_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void enterOrder_specification(SQLParser.Order_specificationContext ctx) {
    super.enterOrder_specification(ctx);
    log("enterOrder_specification: {0}", ctx.start.getText());
  }

  @Override
  public void exitOrder_specification(SQLParser.Order_specificationContext ctx) {
    super.exitOrder_specification(ctx);
    log("exitOrder_specification: {0}", ctx.start.getText());
  }

  @Override
  public void enterParenthesized_value_expression(
      SQLParser.Parenthesized_value_expressionContext ctx) {
    super.enterParenthesized_value_expression(ctx);
    log("enterParenthesized_value_expression: {0}", ctx.start.getText());
  }

  @Override
  public void exitParenthesized_value_expression(
      SQLParser.Parenthesized_value_expressionContext ctx) {
    super.exitParenthesized_value_expression(ctx);
    log("exitParenthesized_value_expression: {0}", ctx.start.getText());
  }

  @Override
  public void enterSimple_when_clause(SQLParser.Simple_when_clauseContext ctx) {
    super.enterSimple_when_clause(ctx);
    log("enterSimple_when_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitSimple_when_clause(SQLParser.Simple_when_clauseContext ctx) {
    super.exitSimple_when_clause(ctx);
    log("exitSimple_when_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterList_value_clause_list(SQLParser.List_value_clause_listContext ctx) {
    super.enterList_value_clause_list(ctx);
    log("enterList_value_clause_list: {0}", ctx.start.getText());
  }

  @Override
  public void exitList_value_clause_list(SQLParser.List_value_clause_listContext ctx) {
    super.exitList_value_clause_list(ctx);
    log("exitList_value_clause_list: {0}", ctx.start.getText());
  }

  @Override
  public void enterFilter_clause(SQLParser.Filter_clauseContext ctx) {
    super.enterFilter_clause(ctx);
    log("enterFilter_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitFilter_clause(SQLParser.Filter_clauseContext ctx) {
    super.exitFilter_clause(ctx);
    log("exitFilter_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterOrderby_clause(SQLParser.Orderby_clauseContext ctx) {
    super.enterOrderby_clause(ctx);
    log("enterOrderby_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitOrderby_clause(SQLParser.Orderby_clauseContext ctx) {
    super.exitOrderby_clause(ctx);
    log("exitOrderby_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterAll(SQLParser.AllContext ctx) {
    super.enterAll(ctx);
    log("enterAll: {0}", ctx.start.getText());
  }

  @Override
  public void exitAll(SQLParser.AllContext ctx) {
    super.exitAll(ctx);
    log("exitAll: {0}", ctx.start.getText());
  }

  @Override
  public void enterData_change_statement(SQLParser.Data_change_statementContext ctx) {
    super.enterData_change_statement(ctx);
    log("enterData_change_statement: {0}", ctx.start.getText());
  }

  @Override
  public void exitData_change_statement(SQLParser.Data_change_statementContext ctx) {
    super.exitData_change_statement(ctx);
    log("exitData_change_statement: {0}", ctx.start.getText());
  }

  @Override
  public void enterWhere_clause(SQLParser.Where_clauseContext ctx) {
    super.enterWhere_clause(ctx);
    log("enterWhere_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitWhere_clause(SQLParser.Where_clauseContext ctx) {
    super.exitWhere_clause(ctx);
    log("exitWhere_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterField_element(SQLParser.Field_elementContext ctx) {
    super.enterField_element(ctx);
    log("enterField_element: {0}", ctx.start.getText());
  }

  @Override
  public void exitField_element(SQLParser.Field_elementContext ctx) {
    super.exitField_element(ctx);
    log("exitField_element: {0}", ctx.start.getText());
  }

  @Override
  public void enterNon_join_query_term(SQLParser.Non_join_query_termContext ctx) {
    super.enterNon_join_query_term(ctx);
    log("enterNon_join_query_term: {0}", ctx.start.getText());
  }

  @Override
  public void exitNon_join_query_term(SQLParser.Non_join_query_termContext ctx) {
    super.exitNon_join_query_term(ctx);
    log("exitNon_join_query_term: {0}", ctx.start.getText());
  }

  @Override
  public void enterNull_ordering(SQLParser.Null_orderingContext ctx) {
    super.enterNull_ordering(ctx);
    log("enterNull_ordering: {0}", ctx.start.getText());
  }

  @Override
  public void exitNull_ordering(SQLParser.Null_orderingContext ctx) {
    super.exitNull_ordering(ctx);
    log("exitNull_ordering: {0}", ctx.start.getText());
  }

  @Override
  public void enterNon_second_primary_datetime_field(
      SQLParser.Non_second_primary_datetime_fieldContext ctx) {
    super.enterNon_second_primary_datetime_field(ctx);
    log("enterNon_second_primary_datetime_field: {0}", ctx.start.getText());
  }

  @Override
  public void exitNon_second_primary_datetime_field(
      SQLParser.Non_second_primary_datetime_fieldContext ctx) {
    super.exitNon_second_primary_datetime_field(ctx);
    log("exitNon_second_primary_datetime_field: {0}", ctx.start.getText());
  }

  @Override
  public void enterValue_expression(SQLParser.Value_expressionContext ctx) {
    super.enterValue_expression(ctx);
    log("enterValue_expression: {0}", ctx.start.getText());
  }

  @Override
  public void exitValue_expression(SQLParser.Value_expressionContext ctx) {
    super.exitValue_expression(ctx);
    log("exitValue_expression: {0}", ctx.start.getText());
  }

  @Override
  public void enterOrdinary_grouping_set_list(SQLParser.Ordinary_grouping_set_listContext ctx) {
    super.enterOrdinary_grouping_set_list(ctx);
    log("enterOrdinary_grouping_set_list: {0}", ctx.start.getText());
  }

  @Override
  public void exitOrdinary_grouping_set_list(SQLParser.Ordinary_grouping_set_listContext ctx) {
    super.exitOrdinary_grouping_set_list(ctx);
    log("exitOrdinary_grouping_set_list: {0}", ctx.start.getText());
  }

  @Override
  public void enterJoin_condition(SQLParser.Join_conditionContext ctx) {
    super.enterJoin_condition(ctx);
    log("enterJoin_condition: {0}", ctx.start.getText());
  }

  @Override
  public void exitJoin_condition(SQLParser.Join_conditionContext ctx) {
    super.exitJoin_condition(ctx);
    log("exitJoin_condition: {0}", ctx.start.getText());
  }

  @Override
  public void enterNegativable_matcher(SQLParser.Negativable_matcherContext ctx) {
    super.enterNegativable_matcher(ctx);
    log("enterNegativable_matcher: {0}", ctx.start.getText());
  }

  @Override
  public void exitNegativable_matcher(SQLParser.Negativable_matcherContext ctx) {
    super.exitNegativable_matcher(ctx);
    log("exitNegativable_matcher: {0}", ctx.start.getText());
  }

  @Override
  public void enterQualified_asterisk(SQLParser.Qualified_asteriskContext ctx) {
    super.enterQualified_asterisk(ctx);
    log("enterQualified_asterisk: {0}", ctx.start.getText());
  }

  @Override
  public void exitQualified_asterisk(SQLParser.Qualified_asteriskContext ctx) {
    super.exitQualified_asterisk(ctx);
    log("exitQualified_asterisk: {0}", ctx.start.getText());
  }

  @Override
  public void enterGeneral_set_function(SQLParser.General_set_functionContext ctx) {
    super.enterGeneral_set_function(ctx);
    log("enterGeneral_set_function: {0}", ctx.start.getText());
  }

  @Override
  public void exitGeneral_set_function(SQLParser.General_set_functionContext ctx) {
    super.exitGeneral_set_function(ctx);
    log("exitGeneral_set_function: {0}", ctx.start.getText());
  }

  @Override
  public void enterNon_join_query_primary(SQLParser.Non_join_query_primaryContext ctx) {
    super.enterNon_join_query_primary(ctx);
    log("enterNon_join_query_primary: {0}", ctx.start.getText());
  }

  @Override
  public void exitNon_join_query_primary(SQLParser.Non_join_query_primaryContext ctx) {
    super.exitNon_join_query_primary(ctx);
    log("exitNon_join_query_primary: {0}", ctx.start.getText());
  }

  @Override
  public void enterQuantifier(SQLParser.QuantifierContext ctx) {
    super.enterQuantifier(ctx);
    log("enterQuantifier: {0}", ctx.start.getText());
  }

  @Override
  public void exitQuantifier(SQLParser.QuantifierContext ctx) {
    super.exitQuantifier(ctx);
    log("exitQuantifier: {0}", ctx.start.getText());
  }

  @Override
  public void enterIndex_statement(SQLParser.Index_statementContext ctx) {
    super.enterIndex_statement(ctx);
    log("enterIndex_statement: {0}", ctx.start.getText());
  }

  @Override
  public void exitIndex_statement(SQLParser.Index_statementContext ctx) {
    super.exitIndex_statement(ctx);
    log("exitIndex_statement: {0}", ctx.start.getText());
  }

  @Override
  public void enterBetween_predicate(SQLParser.Between_predicateContext ctx) {
    super.enterBetween_predicate(ctx);
    log("enterBetween_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void exitBetween_predicate(SQLParser.Between_predicateContext ctx) {
    super.exitBetween_predicate(ctx);
    log("exitBetween_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void enterPrimary_datetime_field(SQLParser.Primary_datetime_fieldContext ctx) {
    super.enterPrimary_datetime_field(ctx);
    log("enterPrimary_datetime_field: {0}", ctx.start.getText());
  }

  @Override
  public void exitPrimary_datetime_field(SQLParser.Primary_datetime_fieldContext ctx) {
    super.exitPrimary_datetime_field(ctx);
    log("exitPrimary_datetime_field: {0}", ctx.start.getText());
  }

  @Override
  public void enterSigned_numerical_literal(SQLParser.Signed_numerical_literalContext ctx) {
    super.enterSigned_numerical_literal(ctx);
    log("enterSigned_numerical_literal: {0}", ctx.start.getText());
  }

  @Override
  public void exitSigned_numerical_literal(SQLParser.Signed_numerical_literalContext ctx) {
    super.exitSigned_numerical_literal(ctx);
    log("exitSigned_numerical_literal: {0}", ctx.start.getText());
  }

  @Override
  public void enterNatural_join(SQLParser.Natural_joinContext ctx) {
    super.enterNatural_join(ctx);
    log("enterNatural_join: {0}", ctx.start.getText());
  }

  @Override
  public void exitNatural_join(SQLParser.Natural_joinContext ctx) {
    super.exitNatural_join(ctx);
    log("exitNatural_join: {0}", ctx.start.getText());
  }

  @Override
  public void enterSql_argument_list(SQLParser.Sql_argument_listContext ctx) {
    super.enterSql_argument_list(ctx);
    log("enterSql_argument_list: {0}", ctx.start.getText());
  }

  @Override
  public void exitSql_argument_list(SQLParser.Sql_argument_listContext ctx) {
    super.exitSql_argument_list(ctx);
    log("exitSql_argument_list: {0}", ctx.start.getText());
  }

  @Override
  public void enterUnique_predicate(SQLParser.Unique_predicateContext ctx) {
    super.enterUnique_predicate(ctx);
    log("enterUnique_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void exitUnique_predicate(SQLParser.Unique_predicateContext ctx) {
    super.exitUnique_predicate(ctx);
    log("exitUnique_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void enterAnd_predicate(SQLParser.And_predicateContext ctx) {
    super.enterAnd_predicate(ctx);
    log("enterAnd_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void exitAnd_predicate(SQLParser.And_predicateContext ctx) {
    super.exitAnd_predicate(ctx);
    log("exitAnd_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void enterQuery_specification(SQLParser.Query_specificationContext ctx) {
    super.enterQuery_specification(ctx);
    log("enterQuery_specification: {0}", ctx.start.getText());
  }

  @Override
  public void exitQuery_specification(SQLParser.Query_specificationContext ctx) {
    super.exitQuery_specification(ctx);
    log("exitQuery_specification: {0}", ctx.start.getText());
  }

  @Override
  public void enterExtract_expression(SQLParser.Extract_expressionContext ctx) {
    super.enterExtract_expression(ctx);
    log("enterExtract_expression: {0}", ctx.start.getText());
  }

  @Override
  public void exitExtract_expression(SQLParser.Extract_expressionContext ctx) {
    super.exitExtract_expression(ctx);
    log("exitExtract_expression: {0}", ctx.start.getText());
  }

  @Override
  public void enterGrouping_element(SQLParser.Grouping_elementContext ctx) {
    super.enterGrouping_element(ctx);
    log("enterGrouping_element: {0}", ctx.start.getText());
  }

  @Override
  public void exitGrouping_element(SQLParser.Grouping_elementContext ctx) {
    super.exitGrouping_element(ctx);
    log("exitGrouping_element: {0}", ctx.start.getText());
  }

  @Override
  public void enterNonreserved_keywords(SQLParser.Nonreserved_keywordsContext ctx) {
    super.enterNonreserved_keywords(ctx);
    log("enterNonreserved_keywords: {0}", ctx.start.getText());
  }

  @Override
  public void exitNonreserved_keywords(SQLParser.Nonreserved_keywordsContext ctx) {
    super.exitNonreserved_keywords(ctx);
    log("exitNonreserved_keywords: {0}", ctx.start.getText());
  }

  @Override
  public void enterSet_function_specification(SQLParser.Set_function_specificationContext ctx) {
    super.enterSet_function_specification(ctx);
    log("enterSet_function_specification: {0}", ctx.start.getText());
  }

  @Override
  public void exitSet_function_specification(SQLParser.Set_function_specificationContext ctx) {
    super.exitSet_function_specification(ctx);
    log("exitSet_function_specification: {0}", ctx.start.getText());
  }

  @Override
  public void enterSome(SQLParser.SomeContext ctx) {
    super.enterSome(ctx);
    log("enterSome: {0}", ctx.start.getText());
  }

  @Override
  public void exitSome(SQLParser.SomeContext ctx) {
    super.exitSome(ctx);
    log("exitSome: {0}", ctx.start.getText());
  }

  @Override
  public void enterTruth_value(SQLParser.Truth_valueContext ctx) {
    super.enterTruth_value(ctx);
    log("enterTruth_value: {0}", ctx.start.getText());
  }

  @Override
  public void exitTruth_value(SQLParser.Truth_valueContext ctx) {
    super.exitTruth_value(ctx);
    log("exitTruth_value: {0}", ctx.start.getText());
  }

  @Override
  public void enterDatetime_type(SQLParser.Datetime_typeContext ctx) {
    super.enterDatetime_type(ctx);
    log("enterDatetime_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitDatetime_type(SQLParser.Datetime_typeContext ctx) {
    super.exitDatetime_type(ctx);
    log("exitDatetime_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterSet_qualifier(SQLParser.Set_qualifierContext ctx) {
    super.enterSet_qualifier(ctx);
    log("enterSet_qualifier: {0}", ctx.start.getText());
  }

  @Override
  public void exitSet_qualifier(SQLParser.Set_qualifierContext ctx) {
    super.exitSet_qualifier(ctx);
    log("exitSet_qualifier: {0}", ctx.start.getText());
  }

  @Override
  public void enterExplicit_table(SQLParser.Explicit_tableContext ctx) {
    super.enterExplicit_table(ctx);
    log("enterExplicit_table: {0}", ctx.start.getText());
  }

  @Override
  public void exitExplicit_table(SQLParser.Explicit_tableContext ctx) {
    super.exitExplicit_table(ctx);
    log("exitExplicit_table: {0}", ctx.start.getText());
  }

  @Override
  public void enterUnsigned_numeric_literal(SQLParser.Unsigned_numeric_literalContext ctx) {
    super.enterUnsigned_numeric_literal(ctx);
    log("enterUnsigned_numeric_literal: {0}", ctx.start.getText());
  }

  @Override
  public void exitUnsigned_numeric_literal(SQLParser.Unsigned_numeric_literalContext ctx) {
    super.exitUnsigned_numeric_literal(ctx);
    log("exitUnsigned_numeric_literal: {0}", ctx.start.getText());
  }

  @Override
  public void enterBoolean_predicand(SQLParser.Boolean_predicandContext ctx) {
    super.enterBoolean_predicand(ctx);
    log("enterBoolean_predicand: {0}", ctx.start.getText());
  }

  @Override
  public void exitBoolean_predicand(SQLParser.Boolean_predicandContext ctx) {
    super.exitBoolean_predicand(ctx);
    log("exitBoolean_predicand: {0}", ctx.start.getText());
  }

  @Override
  public void enterSearch_condition(SQLParser.Search_conditionContext ctx) {
    super.enterSearch_condition(ctx);
    log("enterSearch_condition: {0}", ctx.start.getText());
  }

  @Override
  public void exitSearch_condition(SQLParser.Search_conditionContext ctx) {
    super.exitSearch_condition(ctx);
    log("exitSearch_condition: {0}", ctx.start.getText());
  }

  @Override
  public void enterBoolean_primary(SQLParser.Boolean_primaryContext ctx) {
    super.enterBoolean_primary(ctx);
    log("enterBoolean_primary: {0}", ctx.start.getText());
  }

  @Override
  public void exitBoolean_primary(SQLParser.Boolean_primaryContext ctx) {
    super.exitBoolean_primary(ctx);
    log("exitBoolean_primary: {0}", ctx.start.getText());
  }

  @Override
  public void enterSearched_when_clause(SQLParser.Searched_when_clauseContext ctx) {
    super.enterSearched_when_clause(ctx);
    log("enterSearched_when_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitSearched_when_clause(SQLParser.Searched_when_clauseContext ctx) {
    super.exitSearched_when_clause(ctx);
    log("exitSearched_when_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterDate_literal(SQLParser.Date_literalContext ctx) {
    super.enterDate_literal(ctx);
    log("enterDate_literal: {0}", ctx.start.getText());
  }

  @Override
  public void exitDate_literal(SQLParser.Date_literalContext ctx) {
    super.exitDate_literal(ctx);
    log("exitDate_literal: {0}", ctx.start.getText());
  }

  @Override
  public void enterCharacter_primary(SQLParser.Character_primaryContext ctx) {
    super.enterCharacter_primary(ctx);
    log("enterCharacter_primary: {0}", ctx.start.getText());
  }

  @Override
  public void exitCharacter_primary(SQLParser.Character_primaryContext ctx) {
    super.exitCharacter_primary(ctx);
    log("exitCharacter_primary: {0}", ctx.start.getText());
  }

  @Override
  public void enterSimple_case(SQLParser.Simple_caseContext ctx) {
    super.enterSimple_case(ctx);
    log("enterSimple_case: {0}", ctx.start.getText());
  }

  @Override
  public void exitSimple_case(SQLParser.Simple_caseContext ctx) {
    super.exitSimple_case(ctx);
    log("exitSimple_case: {0}", ctx.start.getText());
  }

  @Override
  public void enterRange_value_clause(SQLParser.Range_value_clauseContext ctx) {
    super.enterRange_value_clause(ctx);
    log("enterRange_value_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitRange_value_clause(SQLParser.Range_value_clauseContext ctx) {
    super.exitRange_value_clause(ctx);
    log("exitRange_value_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterApproximate_numeric_type(SQLParser.Approximate_numeric_typeContext ctx) {
    super.enterApproximate_numeric_type(ctx);
    log("enterApproximate_numeric_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitApproximate_numeric_type(SQLParser.Approximate_numeric_typeContext ctx) {
    super.exitApproximate_numeric_type(ctx);
    log("exitApproximate_numeric_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterGrouping_operation(SQLParser.Grouping_operationContext ctx) {
    super.enterGrouping_operation(ctx);
    log("enterGrouping_operation: {0}", ctx.start.getText());
  }

  @Override
  public void exitGrouping_operation(SQLParser.Grouping_operationContext ctx) {
    super.exitGrouping_operation(ctx);
    log("exitGrouping_operation: {0}", ctx.start.getText());
  }

  @Override
  public void enterTrim_operands(SQLParser.Trim_operandsContext ctx) {
    super.enterTrim_operands(ctx);
    log("enterTrim_operands: {0}", ctx.start.getText());
  }

  @Override
  public void exitTrim_operands(SQLParser.Trim_operandsContext ctx) {
    super.exitTrim_operands(ctx);
    log("exitTrim_operands: {0}", ctx.start.getText());
  }

  @Override
  public void enterSql(SQLParser.SqlContext ctx) {
    super.enterSql(ctx);
    log("enterSql: {0}", ctx.start.getText());
  }

  @Override
  public void exitSql(SQLParser.SqlContext ctx) {
    super.exitSql(ctx);
    log("exitSql: {0}", ctx.start.getText());
  }

  @Override
  public void enterTable_subquery(SQLParser.Table_subqueryContext ctx) {
    super.enterTable_subquery(ctx);
    log("enterTable_subquery: {0}", ctx.start.getText());
  }

  @Override
  public void exitTable_subquery(SQLParser.Table_subqueryContext ctx) {
    super.exitTable_subquery(ctx);
    log("exitTable_subquery: {0}", ctx.start.getText());
  }

  @Override
  public void enterOuter_join_type_part2(SQLParser.Outer_join_type_part2Context ctx) {
    super.enterOuter_join_type_part2(ctx);
    log("enterOuter_join_type_part2: {0}", ctx.start.getText());
  }

  @Override
  public void exitOuter_join_type_part2(SQLParser.Outer_join_type_part2Context ctx) {
    super.exitOuter_join_type_part2(ctx);
    log("exitOuter_join_type_part2: {0}", ctx.start.getText());
  }

  @Override
  public void enterCross_join(SQLParser.Cross_joinContext ctx) {
    super.enterCross_join(ctx);
    log("enterCross_join: {0}", ctx.start.getText());
  }

  @Override
  public void exitCross_join(SQLParser.Cross_joinContext ctx) {
    super.exitCross_join(ctx);
    log("exitCross_join: {0}", ctx.start.getText());
  }

  @Override
  public void enterUnsigned_literal(SQLParser.Unsigned_literalContext ctx) {
    super.enterUnsigned_literal(ctx);
    log("enterUnsigned_literal: {0}", ctx.start.getText());
  }

  @Override
  public void exitUnsigned_literal(SQLParser.Unsigned_literalContext ctx) {
    super.exitUnsigned_literal(ctx);
    log("exitUnsigned_literal: {0}", ctx.start.getText());
  }

  @Override
  public void enterRow_subquery(SQLParser.Row_subqueryContext ctx) {
    super.enterRow_subquery(ctx);
    log("enterRow_subquery: {0}", ctx.start.getText());
  }

  @Override
  public void exitRow_subquery(SQLParser.Row_subqueryContext ctx) {
    super.exitRow_subquery(ctx);
    log("exitRow_subquery: {0}", ctx.start.getText());
  }

  @Override
  public void enterNumeric_value_function(SQLParser.Numeric_value_functionContext ctx) {
    super.enterNumeric_value_function(ctx);
    log("enterNumeric_value_function: {0}", ctx.start.getText());
  }

  @Override
  public void exitNumeric_value_function(SQLParser.Numeric_value_functionContext ctx) {
    super.exitNumeric_value_function(ctx);
    log("exitNumeric_value_function: {0}", ctx.start.getText());
  }

  @Override
  public void enterPredefined_type(SQLParser.Predefined_typeContext ctx) {
    super.enterPredefined_type(ctx);
    log("enterPredefined_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitPredefined_type(SQLParser.Predefined_typeContext ctx) {
    super.exitPredefined_type(ctx);
    log("exitPredefined_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterSelect_sublist(SQLParser.Select_sublistContext ctx) {
    super.enterSelect_sublist(ctx);
    log("enterSelect_sublist: {0}", ctx.start.getText());
  }

  @Override
  public void exitSelect_sublist(SQLParser.Select_sublistContext ctx) {
    super.exitSelect_sublist(ctx);
    log("exitSelect_sublist: {0}", ctx.start.getText());
  }

  @Override
  public void enterQuantified_comparison_predicate(
      SQLParser.Quantified_comparison_predicateContext ctx) {
    super.enterQuantified_comparison_predicate(ctx);
    log("enterQuantified_comparison_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void exitQuantified_comparison_predicate(
      SQLParser.Quantified_comparison_predicateContext ctx) {
    super.exitQuantified_comparison_predicate(ctx);
    log("exitQuantified_comparison_predicate: {0}", ctx.start.getText());
  }

  @Override
  public void enterTable_partitioning_clauses(SQLParser.Table_partitioning_clausesContext ctx) {
    super.enterTable_partitioning_clauses(ctx);
    log("enterTable_partitioning_clauses: {0}", ctx.start.getText());
  }

  @Override
  public void exitTable_partitioning_clauses(SQLParser.Table_partitioning_clausesContext ctx) {
    super.exitTable_partitioning_clauses(ctx);
    log("exitTable_partitioning_clauses: {0}", ctx.start.getText());
  }

  @Override
  public void enterExact_numeric_type(SQLParser.Exact_numeric_typeContext ctx) {
    super.enterExact_numeric_type(ctx);
    log("enterExact_numeric_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitExact_numeric_type(SQLParser.Exact_numeric_typeContext ctx) {
    super.exitExact_numeric_type(ctx);
    log("exitExact_numeric_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterIn_value_list(SQLParser.In_value_listContext ctx) {
    super.enterIn_value_list(ctx);
    log("enterIn_value_list: {0}", ctx.start.getText());
  }

  @Override
  public void exitIn_value_list(SQLParser.In_value_listContext ctx) {
    super.exitIn_value_list(ctx);
    log("exitIn_value_list: {0}", ctx.start.getText());
  }

  @Override
  public void enterNumeric_primary(SQLParser.Numeric_primaryContext ctx) {
    super.enterNumeric_primary(ctx);
    log("enterNumeric_primary: {0}", ctx.start.getText());
  }

  @Override
  public void exitNumeric_primary(SQLParser.Numeric_primaryContext ctx) {
    super.exitNumeric_primary(ctx);
    log("exitNumeric_primary: {0}", ctx.start.getText());
  }

  @Override
  public void enterTrim_function(SQLParser.Trim_functionContext ctx) {
    super.enterTrim_function(ctx);
    log("enterTrim_function: {0}", ctx.start.getText());
  }

  @Override
  public void exitTrim_function(SQLParser.Trim_functionContext ctx) {
    super.exitTrim_function(ctx);
    log("exitTrim_function: {0}", ctx.start.getText());
  }

  @Override
  public void enterGroupby_clause(SQLParser.Groupby_clauseContext ctx) {
    super.enterGroupby_clause(ctx);
    log("enterGroupby_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitGroupby_clause(SQLParser.Groupby_clauseContext ctx) {
    super.exitGroupby_clause(ctx);
    log("exitGroupby_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterRow_value_expression(SQLParser.Row_value_expressionContext ctx) {
    super.enterRow_value_expression(ctx);
    log("enterRow_value_expression: {0}", ctx.start.getText());
  }

  @Override
  public void exitRow_value_expression(SQLParser.Row_value_expressionContext ctx) {
    super.exitRow_value_expression(ctx);
    log("exitRow_value_expression: {0}", ctx.start.getText());
  }

  @Override
  public void enterNational_character_string_type(
      SQLParser.National_character_string_typeContext ctx) {
    super.enterNational_character_string_type(ctx);
    log("enterNational_character_string_type: {0}", ctx.start.getText());
  }

  @Override
  public void exitNational_character_string_type(
      SQLParser.National_character_string_typeContext ctx) {
    super.exitNational_character_string_type(ctx);
    log("exitNational_character_string_type: {0}", ctx.start.getText());
  }

  @Override
  public void enterTrim_specification(SQLParser.Trim_specificationContext ctx) {
    super.enterTrim_specification(ctx);
    log("enterTrim_specification: {0}", ctx.start.getText());
  }

  @Override
  public void exitTrim_specification(SQLParser.Trim_specificationContext ctx) {
    super.exitTrim_specification(ctx);
    log("exitTrim_specification: {0}", ctx.start.getText());
  }

  @Override
  public void enterRow_value_predicand(SQLParser.Row_value_predicandContext ctx) {
    super.enterRow_value_predicand(ctx);
    log("enterRow_value_predicand: {0}", ctx.start.getText());
  }

  @Override
  public void exitRow_value_predicand(SQLParser.Row_value_predicandContext ctx) {
    super.exitRow_value_predicand(ctx);
    log("exitRow_value_predicand: {0}", ctx.start.getText());
  }

  @Override
  public void enterQualified_join(SQLParser.Qualified_joinContext ctx) {
    super.enterQualified_join(ctx);
    log("enterQualified_join: {0}", ctx.start.getText());
  }

  @Override
  public void exitQualified_join(SQLParser.Qualified_joinContext ctx) {
    super.exitQualified_join(ctx);
    log("exitQualified_join: {0}", ctx.start.getText());
  }

  @Override
  public void enterType_length(SQLParser.Type_lengthContext ctx) {
    super.enterType_length(ctx);
    log("enterType_length: {0}", ctx.start.getText());
  }

  @Override
  public void exitType_length(SQLParser.Type_lengthContext ctx) {
    super.exitType_length(ctx);
    log("exitType_length: {0}", ctx.start.getText());
  }

  @Override
  public void enterElse_clause(SQLParser.Else_clauseContext ctx) {
    super.enterElse_clause(ctx);
    log("enterElse_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitElse_clause(SQLParser.Else_clauseContext ctx) {
    super.exitElse_clause(ctx);
    log("exitElse_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterStatement(SQLParser.StatementContext ctx) {
    super.enterStatement(ctx);
    log("enterStatement: {0}", ctx.start.getText());
  }

  @Override
  public void exitStatement(SQLParser.StatementContext ctx) {
    super.exitStatement(ctx);
    log("exitStatement: {0}", ctx.start.getText());
  }

  @Override
  public void enterColumn_reference(SQLParser.Column_referenceContext ctx) {
    super.enterColumn_reference(ctx);
    log("enterColumn_reference: {0}", ctx.start.getText());
  }

  @Override
  public void exitColumn_reference(SQLParser.Column_referenceContext ctx) {
    super.exitColumn_reference(ctx);
    log("exitColumn_reference: {0}", ctx.start.getText());
  }

  @Override
  public void enterUnion_join(SQLParser.Union_joinContext ctx) {
    super.enterUnion_join(ctx);
    log("enterUnion_join: {0}", ctx.start.getText());
  }

  @Override
  public void exitUnion_join(SQLParser.Union_joinContext ctx) {
    super.exitUnion_join(ctx);
    log("exitUnion_join: {0}", ctx.start.getText());
  }

  @Override
  public void enterTimestamp_literal(SQLParser.Timestamp_literalContext ctx) {
    super.enterTimestamp_literal(ctx);
    log("enterTimestamp_literal: {0}", ctx.start.getText());
  }

  @Override
  public void exitTimestamp_literal(SQLParser.Timestamp_literalContext ctx) {
    super.exitTimestamp_literal(ctx);
    log("exitTimestamp_literal: {0}", ctx.start.getText());
  }

  @Override
  public void enterPartition_name(SQLParser.Partition_nameContext ctx) {
    super.enterPartition_name(ctx);
    log("enterPartition_name: {0}", ctx.start.getText());
  }

  @Override
  public void exitPartition_name(SQLParser.Partition_nameContext ctx) {
    super.exitPartition_name(ctx);
    log("exitPartition_name: {0}", ctx.start.getText());
  }

  @Override
  public void enterBoolean_value_expression(SQLParser.Boolean_value_expressionContext ctx) {
    super.enterBoolean_value_expression(ctx);
    log("enterBoolean_value_expression: {0}", ctx.start.getText());
  }

  @Override
  public void exitBoolean_value_expression(SQLParser.Boolean_value_expressionContext ctx) {
    super.exitBoolean_value_expression(ctx);
    log("exitBoolean_value_expression: {0}", ctx.start.getText());
  }

  @Override
  public void enterHash_partitions_by_quantity(SQLParser.Hash_partitions_by_quantityContext ctx) {
    super.enterHash_partitions_by_quantity(ctx);
    log("enterHash_partitions_by_quantity: {0}", ctx.start.getText());
  }

  @Override
  public void exitHash_partitions_by_quantity(SQLParser.Hash_partitions_by_quantityContext ctx) {
    super.exitHash_partitions_by_quantity(ctx);
    log("exitHash_partitions_by_quantity: {0}", ctx.start.getText());
  }

  @Override
  public void enterExtract_source(SQLParser.Extract_sourceContext ctx) {
    super.enterExtract_source(ctx);
    log("enterExtract_source: {0}", ctx.start.getText());
  }

  @Override
  public void exitExtract_source(SQLParser.Extract_sourceContext ctx) {
    super.exitExtract_source(ctx);
    log("exitExtract_source: {0}", ctx.start.getText());
  }

  @Override
  public void enterTable_space_name(SQLParser.Table_space_nameContext ctx) {
    super.enterTable_space_name(ctx);
    log("enterTable_space_name: {0}", ctx.start.getText());
  }

  @Override
  public void exitTable_space_name(SQLParser.Table_space_nameContext ctx) {
    super.exitTable_space_name(ctx);
    log("exitTable_space_name: {0}", ctx.start.getText());
  }

  @Override
  public void enterList_value_partition(SQLParser.List_value_partitionContext ctx) {
    super.enterList_value_partition(ctx);
    log("enterList_value_partition: {0}", ctx.start.getText());
  }

  @Override
  public void exitList_value_partition(SQLParser.List_value_partitionContext ctx) {
    super.exitList_value_partition(ctx);
    log("exitList_value_partition: {0}", ctx.start.getText());
  }

  @Override
  public void enterList_partitions(SQLParser.List_partitionsContext ctx) {
    super.enterList_partitions(ctx);
    log("enterList_partitions: {0}", ctx.start.getText());
  }

  @Override
  public void exitList_partitions(SQLParser.List_partitionsContext ctx) {
    super.exitList_partitions(ctx);
    log("exitList_partitions: {0}", ctx.start.getText());
  }

  @Override
  public void enterExtract_field(SQLParser.Extract_fieldContext ctx) {
    super.enterExtract_field(ctx);
    log("enterExtract_field: {0}", ctx.start.getText());
  }

  @Override
  public void exitExtract_field(SQLParser.Extract_fieldContext ctx) {
    super.exitExtract_field(ctx);
    log("exitExtract_field: {0}", ctx.start.getText());
  }

  @Override
  public void enterHash_partitions(SQLParser.Hash_partitionsContext ctx) {
    super.enterHash_partitions(ctx);
    log("enterHash_partitions: {0}", ctx.start.getText());
  }

  @Override
  public void exitHash_partitions(SQLParser.Hash_partitionsContext ctx) {
    super.exitHash_partitions(ctx);
    log("exitHash_partitions: {0}", ctx.start.getText());
  }

  @Override
  public void enterCreate_table_statement(SQLParser.Create_table_statementContext ctx) {
    super.enterCreate_table_statement(ctx);
    log("enterCreate_table_statement: {0}", ctx.start.getText());
  }

  @Override
  public void exitCreate_table_statement(SQLParser.Create_table_statementContext ctx) {
    super.exitCreate_table_statement(ctx);
    log("exitCreate_table_statement: {0}", ctx.start.getText());
  }

  @Override
  public void enterCast_specification(SQLParser.Cast_specificationContext ctx) {
    super.enterCast_specification(ctx);
    log("enterCast_specification: {0}", ctx.start.getText());
  }

  @Override
  public void exitCast_specification(SQLParser.Cast_specificationContext ctx) {
    super.exitCast_specification(ctx);
    log("exitCast_specification: {0}", ctx.start.getText());
  }

  @Override
  public void enterTable_name(SQLParser.Table_nameContext ctx) {
    super.enterTable_name(ctx);
    log("enterTable_name: {0}", ctx.start.getText());
  }

  @Override
  public void exitTable_name(SQLParser.Table_nameContext ctx) {
    super.exitTable_name(ctx);
    log("exitTable_name: {0}", ctx.start.getText());
  }

  @Override
  public void enterAs_clause(SQLParser.As_clauseContext ctx) {
    super.enterAs_clause(ctx);
    log("enterAs_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitAs_clause(SQLParser.As_clauseContext ctx) {
    super.exitAs_clause(ctx);
    log("exitAs_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterSort_specifier(SQLParser.Sort_specifierContext ctx) {
    super.enterSort_specifier(ctx);
    log("enterSort_specifier: {0}", ctx.start.getText());
  }

  @Override
  public void exitSort_specifier(SQLParser.Sort_specifierContext ctx) {
    super.exitSort_specifier(ctx);
    log("exitSort_specifier: {0}", ctx.start.getText());
  }

  @Override
  public void enterRow_value_predicand_list(SQLParser.Row_value_predicand_listContext ctx) {
    super.enterRow_value_predicand_list(ctx);
    log("enterRow_value_predicand_list: {0}", ctx.start.getText());
  }

  @Override
  public void exitRow_value_predicand_list(SQLParser.Row_value_predicand_listContext ctx) {
    super.exitRow_value_predicand_list(ctx);
    log("exitRow_value_predicand_list: {0}", ctx.start.getText());
  }

  @Override
  public void enterTable_elements(SQLParser.Table_elementsContext ctx) {
    super.enterTable_elements(ctx);
    log("enterTable_elements: {0}", ctx.start.getText());
  }

  @Override
  public void exitTable_elements(SQLParser.Table_elementsContext ctx) {
    super.exitTable_elements(ctx);
    log("exitTable_elements: {0}", ctx.start.getText());
  }

  @Override
  public void enterRange_value_clause_list(SQLParser.Range_value_clause_listContext ctx) {
    super.enterRange_value_clause_list(ctx);
    log("enterRange_value_clause_list: {0}", ctx.start.getText());
  }

  @Override
  public void exitRange_value_clause_list(SQLParser.Range_value_clause_listContext ctx) {
    super.exitRange_value_clause_list(ctx);
    log("exitRange_value_clause_list: {0}", ctx.start.getText());
  }

  @Override
  public void enterIs_clause(SQLParser.Is_clauseContext ctx) {
    super.enterIs_clause(ctx);
    log("enterIs_clause: {0}", ctx.start.getText());
  }

  @Override
  public void exitIs_clause(SQLParser.Is_clauseContext ctx) {
    super.exitIs_clause(ctx);
    log("exitIs_clause: {0}", ctx.start.getText());
  }

  @Override
  public void enterMethod_specifier(SQLParser.Method_specifierContext ctx) {
    super.enterMethod_specifier(ctx);
    log("enterMethod_specifier: {0}", ctx.start.getText());
  }

  @Override
  public void exitMethod_specifier(SQLParser.Method_specifierContext ctx) {
    super.exitMethod_specifier(ctx);
    log("exitMethod_specifier: {0}", ctx.start.getText());
  }

  @Override
  public void enterBetween_predicate_part_2(SQLParser.Between_predicate_part_2Context ctx) {
    super.enterBetween_predicate_part_2(ctx);
    log("enterBetween_predicate_part_2: {0}", ctx.start.getText());
  }

  @Override
  public void exitBetween_predicate_part_2(SQLParser.Between_predicate_part_2Context ctx) {
    super.exitBetween_predicate_part_2(ctx);
    log("exitBetween_predicate_part_2: {0}", ctx.start.getText());
  }

  @Override
  public void enterSubquery(SQLParser.SubqueryContext ctx) {
    super.enterSubquery(ctx);
    log("enterSubquery: {0}", ctx.start.getText());
  }

  @Override
  public void exitSubquery(SQLParser.SubqueryContext ctx) {
    super.exitSubquery(ctx);
    log("exitSubquery: {0}", ctx.start.getText());
  }

  @Override
  public void enterFunction_names_for_reserved_words(
      SQLParser.Function_names_for_reserved_wordsContext ctx) {
    super.enterFunction_names_for_reserved_words(ctx);
    log("enterFunction_names_for_reserved_words: {0}", ctx.start.getText());
  }

  @Override
  public void exitFunction_names_for_reserved_words(
      SQLParser.Function_names_for_reserved_wordsContext ctx) {
    super.exitFunction_names_for_reserved_words(ctx);
    log("exitFunction_names_for_reserved_words: {0}", ctx.start.getText());
  }

  @Override
  public void enterSearched_case(SQLParser.Searched_caseContext ctx) {
    super.enterSearched_case(ctx);
    log("enterSearched_case: {0}", ctx.start.getText());
  }

  @Override
  public void exitSearched_case(SQLParser.Searched_caseContext ctx) {
    super.exitSearched_case(ctx);
    log("exitSearched_case: {0}", ctx.start.getText());
  }

  @Override
  public void enterNonparenthesized_value_expression_primary(
      SQLParser.Nonparenthesized_value_expression_primaryContext ctx) {
    super.enterNonparenthesized_value_expression_primary(ctx);
    log("enterNonparenthesized_value_expression_primary: {0}", ctx.start.getText());
  }

  @Override
  public void exitNonparenthesized_value_expression_primary(
      SQLParser.Nonparenthesized_value_expression_primaryContext ctx) {
    super.exitNonparenthesized_value_expression_primary(ctx);
    log("exitNonparenthesized_value_expression_primary: {0}", ctx.start.getText());
  }

  @Override
  public void enterRegex_matcher(SQLParser.Regex_matcherContext ctx) {
    super.enterRegex_matcher(ctx);
    log("enterRegex_matcher: {0}", ctx.start.getText());
  }

  @Override
  public void exitRegex_matcher(SQLParser.Regex_matcherContext ctx) {
    super.exitRegex_matcher(ctx);
    log("exitRegex_matcher: {0}", ctx.start.getText());
  }

  @Override
  public void enterTerm(SQLParser.TermContext ctx) {
    super.enterTerm(ctx);
    log("enterTerm: {0}", ctx.start.getText());
  }

  @Override
  public void exitTerm(SQLParser.TermContext ctx) {
    super.exitTerm(ctx);
    log("exitTerm: {0}", ctx.start.getText());
  }

  @Override
  public void enterTable_space_specifier(SQLParser.Table_space_specifierContext ctx) {
    super.enterTable_space_specifier(ctx);
    log("enterTable_space_specifier: {0}", ctx.start.getText());
  }

  @Override
  public void exitTable_space_specifier(SQLParser.Table_space_specifierContext ctx) {
    super.exitTable_space_specifier(ctx);
    log("exitTable_space_specifier: {0}", ctx.start.getText());
  }

  @Override
  public void enterTable_primary(SQLParser.Table_primaryContext ctx) {
    super.enterTable_primary(ctx);
    log("enterTable_primary: {0}", ctx.start.getText());
  }

  @Override
  public void exitTable_primary(SQLParser.Table_primaryContext ctx) {
    super.exitTable_primary(ctx);
    log("exitTable_primary: {0}", ctx.start.getText());
  }

  @Override
  public void visitTerminal(TerminalNode node) {
    super.visitTerminal(node);
    log("visitTerminal: {0}", node.getText());
  }

  @Override
  public void visitErrorNode(ErrorNode node) {
    super.visitErrorNode(node);
    log("visitErrorNode: {0}", node.getText());
  }

  private void log(String message, Object context) {
    if (logger.isLoggable(FINEST)) {
      logger.log(FINEST, message, context);
    }
  }
}
