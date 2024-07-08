package com.algostrategix.alpacatrdeapi.controller;

import com.algostrategix.alpacatrdeapi.client.AlpacaApiClientService;
import com.algostrategix.alpacatrdeapi.model.AccountInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AlpacaController {

    private final AlpacaApiClientService apiClient;

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }

    @GetMapping("/account")
    public ResponseEntity<AccountInfo> getAccountInfo() {
        return apiClient.getAccountInfo();
    }
}