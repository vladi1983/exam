package com.naya.exam;

import com.naya.exam.repository.EventRepo;
import com.naya.exam.repository.PlayerRepo;
import com.naya.exam.service.StatisticGames;
import com.naya.exam.util.convertor.DateFormat;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StartAppTests {

    // Test repositories -------------------------------------------------------------------------
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

    // Test services -------------------------------------------------------------------------
    @Test
    void testSuspiciousActivity() {
        SparkSession ss = SparkSession.builder().master("local[*]").appName("Exam").getOrCreate();
        ss.sparkContext().setLogLevel("ERROR");
        new StatisticGames(new EventRepo(ss), new PlayerRepo(ss))
                .getSuspiciousActivity("20150101", "20200101");
    }

    @Test
    void testAverageMaxMinBet() {
        SparkSession ss = SparkSession.builder().master("local[*]").appName("Exam").getOrCreate();
        ss.sparkContext().setLogLevel("ERROR");
        new StatisticGames(new EventRepo(ss), new PlayerRepo(ss))
                .getAverageMaxMinBet("blackjack-demo", "20150101", "20200101");
    }

    @Test
    void testAverageMaxMinProfit() {
        SparkSession ss = SparkSession.builder().master("local[*]").appName("Exam").getOrCreate();
        ss.sparkContext().setLogLevel("ERROR");
        new StatisticGames(new EventRepo(ss), new PlayerRepo(ss))
                .getAverageMaxMinProfit("blackjack-demo", "20150101", "20200101");
    }

    // Test utils -------------------------------------------------------------------------
    @Test
    void testDataConvert() {
        String s = DateFormat.convertStringToDateFormat("20200326");
    }
}
