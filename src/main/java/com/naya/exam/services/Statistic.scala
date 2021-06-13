package com.naya.exam.services

trait Statistic {
  def getSuspiciousActivity(): Unit

  def getAverageMaxMinBet(nameGame: String, timePeriod: String): String

  def getAverageMaxMinWin(nameGame: String, timePeriod: String): String

  def getAverageMaxMinProfit(nameGame: String, timePeriod: String): String

  def getAverageMaxMinBetOfAllGames(timePeriod: String): String

  def getAverageMaxMinWinOfAllGames(timePeriod: String): String

  def getAverageMaxMinProfitOfAllGames(timePeriod: String): String

}
