package com.naya.exam.models.dto

case class Event(
                  eventId: Int,
                  eventTime: String,
                  eventCountry: String,
                  eventCurrencyCode: String,
                  userId: Int,
                  bet: Double,
                  gameName: String,
                  win: Double,
                  onlineTimeSecs: Int
                )
