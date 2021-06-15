package com.naya.exam.repository

import com.naya.exam.model.Event
import org.apache.spark.sql.{Dataset, Encoders, Row, SparkSession}
import org.springframework.stereotype.Repository

@Repository
class EventRepo(sparkSession: SparkSession) {

  def readEvents(): Dataset[Row] = {
    val schema = Encoders.product[Event].schema
    sparkSession
      .read.option("multiline", "true")
      .schema(schema)
      .json("data/generated_event.json")
  }
}
