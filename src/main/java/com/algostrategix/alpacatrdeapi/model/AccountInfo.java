package com.algostrategix.alpacatrdeapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountInfo {
    private String id;
    private String accountNumber;
    private String status;
    private String currency;
    private double cash;
    private double portfolioValue;
    private boolean patternDayTrader;
    private boolean tradeSuspendedByUser;
    private boolean tradingBlocked;
    private boolean transfersBlocked;
    private boolean accountBlocked;
    private ZonedDateTime createdAt;
    private boolean shortingEnabled;
    private double longMarketValue;
    private double shortMarketValue;
    private double equity;
    private double lastEquity;
    private int multiplier;
    private double buyingPower;
    private double initialMargin;
    private double maintenanceMargin;
    private double sma;
    private int daytradeCount;
    private double lastMaintenanceMargin;
    private double daytradingBuyingPower;
    private double regtBuyingPower;

}
