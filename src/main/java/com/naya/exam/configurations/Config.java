package com.naya.exam.configurations;

import org.apache.spark.sql.SparkSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    SparkSession sparkSessionApp() {
        return SparkSession.builder()
                .master("local[*]")
                .appName("Exam")
                .getOrCreate();
    }
}
