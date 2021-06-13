package com.naya.exam.services

import com.naya.exam.repositories.{EventRepo, PlayerRepo}
import com.naya.exam.utils.validator.ValidatorSuspiciousActivity
import org.apache.spark.sql.Dataset
import org.apache.spark.sql.catalyst.dsl.expressions.{DslExpression, StringToAttributeConversionHelper}
import org.apache.spark.sql.functions.{col, when}

class StatisticGames(eventRepo: EventRepo, playerRepo: PlayerRepo) extends Statistic {
  private val players = playerRepo.readUsers()
  private val events = eventRepo.readEvents()

  override def getSuspiciousActivity(): Unit = {
    events
      .withColumn("bet", when(col("eventCurrencyCode") === "EUR", 777.7))

      .filter(x => ValidatorSuspiciousActivity.higherOnlineTime(x.getInt(8)))
      .filter(x => ValidatorSuspiciousActivity.ratioWinBet(x.getDouble(7), x.getDouble(5)))
      .join(players, events("userId") === players("id"))
      .filter(x => ValidatorSuspiciousActivity.betFromDifferentCountries(x.getString(2), x.getString(12)))
      .filter(x => ValidatorSuspiciousActivity.validForPlayersFromUSA(x.getString(12), x.getString(6)))
      .show(40)

  }

  override def getAverageMaxMinBet(nameGame: String, timePeriod: String): String = ???

  override def getAverageMaxMinWin(nameGame: String, timePeriod: String): String = ???

  override def getAverageMaxMinProfit(nameGame: String, timePeriod: String): String = ???

  override def getAverageMaxMinBetOfAllGames(timePeriod: String): String = ???

  override def getAverageMaxMinWinOfAllGames(timePeriod: String): String = ???

  override def getAverageMaxMinProfitOfAllGames(timePeriod: String): String = ???

}
