package com.naya.exam.model.dto;

import lombok.Data;

@Data
public class ResponseAverageMaxMinProfit {
    private String gameName;
    private Double avg_profit;
    private Double max_profit;
    private Double min_profit;
    private String period_from;
    private String period_to;
}
