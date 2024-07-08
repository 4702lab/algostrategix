package com.algostrategix.alpacatrdeapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "alpaca")
public class AlpacaApiConfig {
    private String apiKey;
    private String apiSecret;
    private String baseUrl;
}
