package com.naya.exam.utils.convertor

import com.naya.exam.models.dto.Event

object ConvertorCurrency {
  def convertEURToUSD(event: Event): Double = {
    var bet = 0.0;
    if (event.currencyCode != "USD") {
      bet = event.bet * 1.1
    }
    bet
  }
}
