package com.naya.exam.util.convertor

import java.text.SimpleDateFormat

object DateFormat {
  def convertStringToDateFormat(message: String): String = {
    val parser = new SimpleDateFormat("yyyyMMdd")
    val formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss ZZZZZ")
    formatter.format(parser.parse(message))
  }
}