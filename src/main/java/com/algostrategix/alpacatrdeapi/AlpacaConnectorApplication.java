package com.algostrategix.alpacatrdeapi;

import com.algostrategix.alpacatrdeapi.config.AlpacaApiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AlpacaApiConfig.class)
public class AlpacaConnectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlpacaConnectorApplication.class, args);
	}
}