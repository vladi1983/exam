package com.naya.exam.repositories

import org.apache.spark.sql.{Dataset, Row, SparkSession}

class PlayerRepo(sparkSession: SparkSession) {

  def readUsers(): Dataset[Row] = {
    sparkSession.read.format("jdbc")
      .option("url", "jdbc:h2:mem:game_platform")
      .option("dbtable", "users")
      .option("user", "sa")
      .option("password", "")
      .load()
  }
}
