package com.naya.exam;

import com.naya.exam.repositories.EventRepo;
import com.naya.exam.repositories.PlayerRepo;
import com.naya.exam.services.StatisticGames;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StartAppTests {

    @Test
    void testEventRepo() {
        System.setProperty("hadoop.home.dir", "/");
        Dataset<Row> dataSetEvent = new EventRepo(
                SparkSession.builder()
                        .master("local[*]")
                        .appName("Exam")
                        .getOrCreate()
        )
                .readEvents();
        dataSetEvent.printSchema();
        dataSetEvent.show();
    }

    @Test
    void testUserRepo() {
        System.setProperty("hadoop.home.dir", "/");
        Dataset<Row> dataSetUsers = new PlayerRepo(
                SparkSession.builder()
                        .master("local[*]")
                        .appName("Exam")
                        .getOrCreate()
        )
                .readUsers();
        dataSetUsers.printSchema();
        dataSetUsers.show();
    }

    @Test
    void testSuspiciousActivity() {
        SparkSession ss = SparkSession.builder().master("local[*]").appName("Exam").getOrCreate();
        new StatisticGames(new EventRepo(ss), new PlayerRepo(ss))
                .getSuspiciousActivity();
    }

}
