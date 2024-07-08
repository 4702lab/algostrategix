package com.algostrategix.alpacatrdeapi.controller;

import com.algostrategix.alpacatrdeapi.dto.UserLoginRequest;
import com.algostrategix.alpacatrdeapi.dto.UserRegistrationRequest;
import com.algostrategix.alpacatrdeapi.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationRequest request) {
        authenticationService.register(
                request.getUserId(),
                request.getPassword(),
                request.getApiKey(),
                request.getApiSecret(),
                request.getAccountType(),
                request.getTokenExpiryDays()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) {
        String token = authenticationService.authenticate(request.getUserId(), request.getPassword());
        return ResponseEntity.ok(token);
    }
}