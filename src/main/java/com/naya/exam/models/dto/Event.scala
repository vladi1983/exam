package com.naya.exam.models.dto

import java.util.Date

case class Event(
                  eventId: Int,
                  eventTime: Date,
                  country: String,
                  currencyCode: String,
                  userId: Int,
                  bet: Double,
                  gameName: String,
                  win: Double,
                  onlineTimeSecs:Int
                )
