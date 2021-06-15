package com.naya.exam.model.dto;

import lombok.Data;

@Data
public class ResponseAverageMaxMinWin {
    private String gameName;
    private Double avg_win;
    private Double max_win;
    private Double min_win;
    private String period_from;
    private String period_to;
}
