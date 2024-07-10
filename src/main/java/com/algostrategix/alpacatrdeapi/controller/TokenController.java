package com.algostrategix.alpacatrdeapi.controller;

import com.algostrategix.alpacatrdeapi.dto.AuthenticationRequest;
import com.algostrategix.alpacatrdeapi.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/token")
public class TokenController {

    private static final Logger logger = LogManager.getLogger(TokenController.class);

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @PostMapping("/generate")
    public ResponseEntity<String> generateToken(@RequestBody AuthenticationRequest authenticationRequest) {
        logger.info("Request received for token generation: {}", authenticationRequest);

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            String token = jwtUtil.generateToken(authenticationRequest.getUsername());
            logger.info("Generated token: {}", token);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            logger.error("Authentication failed for user: {}", authenticationRequest.getUsername(), e);
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }
}