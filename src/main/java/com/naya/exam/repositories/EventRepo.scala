package com.naya.exam.repositories

import com.naya.exam.models.dto.Event
import org.apache.spark.sql.{Dataset, Encoders, Row, SparkSession}

class EventRepo(sparkSession: SparkSession) {

  def readEvents(): Dataset[Row] = {
    val schema = Encoders.product[Event].schema
    sparkSession
      .read.option("multiline", "true")
      .schema(schema)
      .json("data/generated_event.json")
  }
}
