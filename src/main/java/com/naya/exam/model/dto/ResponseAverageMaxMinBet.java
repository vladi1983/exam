package com.naya.exam.model.dto;

import lombok.Data;

@Data
public class ResponseAverageMaxMinBet {
    private String gameName;
    private Double avg_bet;
    private Double max_bet;
    private Double min_bet;
    private String period_from;
    private String period_to;
}
