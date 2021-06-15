package com.naya.exam.model.dto;

import lombok.Data;

@Data
public class ResponseSuspiciousActivity {
    private int eventId;
    private String eventTime;
    private String eventCountry;
    private String eventCurrencyCode;
    private int userId;
    private Double bet;
    private String gameName;
    private Double win;
    private int onlineTimeSecs;
}
