package com.algostrategix.alpacatrdeapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Position {
    private String symbol; // e.g., "AAPL", "MSFT"
    private double quantity;
    private double marketValue;
    private String side; // e.g., "long", "short"
}
