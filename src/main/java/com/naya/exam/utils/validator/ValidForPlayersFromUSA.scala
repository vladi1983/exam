package com.naya.exam.utils.validator

import com.naya.exam.models.dto.Event

object ValidForPlayersFromUSA {

  def isValid(event: Event): Boolean = !event.gameName.contains("demo")
}
