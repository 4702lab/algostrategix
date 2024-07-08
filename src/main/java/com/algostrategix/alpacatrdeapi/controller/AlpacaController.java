package com.algostrategix.alpacatrdeapi.controller;

import com.algostrategix.alpacatrdeapi.model.User;
import com.algostrategix.alpacatrdeapi.service.AlpacaApiClientService;
import com.algostrategix.alpacatrdeapi.model.AccountInfo;
import com.algostrategix.alpacatrdeapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class AlpacaController {

    @Autowired
    private AlpacaApiClientService alpacaApiClientService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/account")
    public ResponseEntity<AccountInfo> getAccountInfo(@RequestHeader("Authorization") String token) {
        User user = authenticationService.validateToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid or expired token"));

        return alpacaApiClientService.getAccountInfo();
    }
}