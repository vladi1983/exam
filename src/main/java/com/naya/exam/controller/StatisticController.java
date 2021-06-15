package com.naya.exam.controller;

import com.naya.exam.model.dto.ResponseAverageMaxMinBet;
import com.naya.exam.model.dto.ResponseAverageMaxMinProfit;
import com.naya.exam.model.dto.ResponseAverageMaxMinWin;
import com.naya.exam.model.dto.ResponseSuspiciousActivity;
import com.naya.exam.service.StatisticGames;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StatisticController {

    private StatisticGames statisticGames;

    public StatisticController(StatisticGames statisticGames) {
        this.statisticGames = statisticGames;
    }

    @GetMapping("/suspicious_activity/{startPeriod}/{endPeriod}")
    public List<ResponseSuspiciousActivity> suspiciousActivity(@PathVariable String startPeriod,
                                                               @PathVariable String endPeriod) {
        Dataset<Row> suspiciousActivity = statisticGames.getSuspiciousActivity(startPeriod, endPeriod);
        return suspiciousActivity.as(Encoders.bean(ResponseSuspiciousActivity.class)).collectAsList();
    }

    @GetMapping("/statistic/bet/{nameGame}/{startPeriod}/{endPeriod}")
    public List<ResponseAverageMaxMinBet> betGameAverageMaxMin(@PathVariable String nameGame,
                                                               @PathVariable String startPeriod,
                                                               @PathVariable String endPeriod) {
        Dataset<Row> averageMaxMinBet =
                statisticGames.getAverageMaxMinBet(nameGame, startPeriod, endPeriod);
        return averageMaxMinBet.as(Encoders.bean(ResponseAverageMaxMinBet.class)).collectAsList();
    }

    @GetMapping("/statistic/win/{nameGame}/{startPeriod}/{endPeriod}")
    public List<ResponseAverageMaxMinWin> winGameAverageMaxMin(@PathVariable String nameGame,
                                                               @PathVariable String startPeriod,
                                                               @PathVariable String endPeriod) {
        Dataset<Row> averageMaxMinWin =
                statisticGames.getAverageMaxMinWin(nameGame, startPeriod, endPeriod);
        return averageMaxMinWin.as(Encoders.bean(ResponseAverageMaxMinWin.class)).collectAsList();
    }

    @GetMapping("/statistic/profit/{nameGame}/{startPeriod}/{endPeriod}")
    public List<ResponseAverageMaxMinProfit> profitGameAverageMaxMin(@PathVariable String nameGame,
                                                                     @PathVariable String startPeriod,
                                                                     @PathVariable String endPeriod) {
        Dataset<Row> averageMaxMinProfit =
                statisticGames.getAverageMaxMinProfit(nameGame, startPeriod, endPeriod);
        return averageMaxMinProfit.as(Encoders.bean(ResponseAverageMaxMinProfit.class)).collectAsList();
    }
}
