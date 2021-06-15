package com.naya.exam.service

import com.naya.exam.repository.{EventRepo, PlayerRepo}
import com.naya.exam.util.convertor.DateFormat
import com.naya.exam.util.validator.ValidatorSuspiciousActivity
import org.apache.spark.sql.{Dataset, Row}
import org.apache.spark.sql.functions.{avg, col, lit, max, min, when}
import org.springframework.stereotype.Service

@Service
class StatisticGames(eventRepo: EventRepo, playerRepo: PlayerRepo) {

  private val players = playerRepo.readUsers()
  private val events = eventRepo.readEvents()

  def getSuspiciousActivity(startPeriod: String, endPeriod: String): Dataset[Row] = {
    events
      .filter(x => ValidatorSuspiciousActivity.higherOnlineTime(x.getInt(8)))
      .filter(col("eventTime").gt(lit(DateFormat.convertStringToDateFormat(startPeriod))))
      .filter(col("eventTime").lt(lit(DateFormat.convertStringToDateFormat(endPeriod))))
      .filter(x => ValidatorSuspiciousActivity.ratioWinBet(x.getDouble(7), x.getDouble(5)))

      .join(players, events("userId") === players("id"))

      .withColumn("win", when(col("eventCurrencyCode") === "EUR",
        col("win") * 1.10.toInt).otherwise(col("win")))
      .withColumn("eventCurrencyCode", when(col("eventCurrencyCode") === "EUR",
        "USD").otherwise(col("eventCurrencyCode")))

      .filter(x => ValidatorSuspiciousActivity.betFromDifferentCountries(x.getString(2), x.getString(12)))
      .filter(x => ValidatorSuspiciousActivity.validForPlayersFromUSA(x.getString(12), x.getString(6)))
  }

  def getAverageMaxMinBet(nameGame: String, startPeriod: String, endPeriod: String): Dataset[Row] = {
    events
      .filter(col("gameName") === nameGame || nameGame == "all_games")
      .filter(col("eventTime").gt(lit(DateFormat.convertStringToDateFormat(startPeriod))))
      .filter(col("eventTime").lt(lit(DateFormat.convertStringToDateFormat(endPeriod))))
      .groupBy("gameName")
      .agg(
        avg("bet").as("avg_bet"),
        max("bet").as("max_bet"),
        min("bet").as("min_bet")
      )
      .withColumn("period_from", lit(startPeriod))
      .withColumn("period_to", lit(endPeriod))
  }

  def getAverageMaxMinWin(nameGame: String, startPeriod: String, endPeriod: String): Dataset[Row] = {
    events
      .filter(col("gameName") === nameGame || nameGame == "all_games")
      .filter(col("eventTime").gt(lit(DateFormat.convertStringToDateFormat(startPeriod))))
      .filter(col("eventTime").lt(lit(DateFormat.convertStringToDateFormat(endPeriod))))
      .groupBy("gameName")
      .agg(
        avg("win").as("avg_win"),
        max("win").as("max_win"),
        min("win").as("min_win")
      )
      .withColumn("period_from", lit(startPeriod))
      .withColumn("period_to", lit(endPeriod))
  }

  def getAverageMaxMinProfit(nameGame: String, startPeriod: String, endPeriod: String): Dataset[Row] = {
    events
      .filter(col("gameName") === nameGame || nameGame == "all_games")
      .filter(col("eventTime").gt(lit(DateFormat.convertStringToDateFormat(startPeriod))))
      .filter(col("eventTime").lt(lit(DateFormat.convertStringToDateFormat(endPeriod))))
      .groupBy("gameName")
      .agg(
        avg(col("win") - (col("win") / col("bet"))).as("avg_profit"),
        max(col("win") - (col("win") / col("bet"))).as("max_profit"),
        min(col("win") - (col("win") / col("bet"))).as("min_profit"))
      .withColumn("period_from", lit(startPeriod))
      .withColumn("period_to", lit(endPeriod))
  }
}
