package com.algostrategix.alpacatrdeapi.dto;

import com.algostrategix.alpacatrdeapi.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationRequest {
    private String userId;
    private String password;
    private String apiKey;
    private String apiSecret;
    private AccountType accountType;
    private int tokenExpiryDays;
}
