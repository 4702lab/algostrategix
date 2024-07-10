package com.algostrategix.alpacatrdeapi.controller;

import com.algostrategix.alpacatrdeapi.dto.AuthenticationRequest;
import com.algostrategix.alpacatrdeapi.dto.UserRegistrationRequest;
import com.algostrategix.alpacatrdeapi.service.AuthenticationService;
import com.algostrategix.alpacatrdeapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

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
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        final String jwt = jwtUtil.generateToken(authenticationRequest.getUsername());

        return ResponseEntity.ok(jwt);
    }
}