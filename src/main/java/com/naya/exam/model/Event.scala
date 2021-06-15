package com.naya.exam.model

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
