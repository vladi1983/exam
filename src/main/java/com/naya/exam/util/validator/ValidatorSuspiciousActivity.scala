package com.naya.exam.util.validator

object ValidatorSuspiciousActivity {

  def betFromDifferentCountries(eventCountry: String, countryOfOrigin: String): Boolean = {
    eventCountry != countryOfOrigin
  }

  def ratioWinBet(win: Double, bet: Double): Boolean = bet * 10.0 < win

  def higherOnlineTime(second: Int): Boolean = second > 18000

  def validForPlayersFromUSA(countryOfOrigin: String, getName: String): Boolean = {
    if (countryOfOrigin == "USA" && getName.contains("demo")) {
      false
    } else true
  }
}
