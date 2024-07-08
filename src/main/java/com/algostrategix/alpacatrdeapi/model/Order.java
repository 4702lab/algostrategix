package com.algostrategix.alpacatrdeapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private String symbol; // e.g., "AAPL", "MSFT"
    private double quantity;
    private String side; // e.g., "buy", "sell"
    private double limitPrice;
    private String orderType; // e.g., "market", "limit"
}
