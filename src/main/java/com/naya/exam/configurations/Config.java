package com.naya.exam.configurations;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

//    @Bean
//    public SparkContext sc() {
//        SparkConf conf = new SparkConf();
//        conf.setAppName("exam");
//        conf.setMaster("local[*]");
//        return new SparkContext(conf);
//    }
//
//    @Bean
//    public SQLContext sqlContext() {
//        return new SQLContext(sc());
//    }
}
