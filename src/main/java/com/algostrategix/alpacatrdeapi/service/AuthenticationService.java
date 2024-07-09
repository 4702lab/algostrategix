package com.algostrategix.alpacatrdeapi.service;

import com.algostrategix.alpacatrdeapi.model.AuthToken;
import com.algostrategix.alpacatrdeapi.model.User;
import com.algostrategix.alpacatrdeapi.enums.AccountType;
import com.algostrategix.alpacatrdeapi.repository.AuthTokenRepository;
import com.algostrategix.alpacatrdeapi.repository.UserRepository;
import com.algostrategix.alpacatrdeapi.util.EncryptionService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthenticationService {
    private static final String SECRET_KEY = "secret";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthTokenRepository authTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EncryptionService encryptionService;

    public AuthenticationService() {
    }

    public User register(String userId, String password, String apiKey, String apiSecret, AccountType accountType, int tokenExpiryDays) {
        String passwordHash = passwordEncoder.encode(password);
        String apiKeyEnc = encryptionService.encrypt(apiKey);
        String apiSecretEnc = encryptionService.encrypt(apiSecret);

        User user = new User();
        user.setUserId(userId);
        user.setPasswordHash(passwordHash);
        user.setApiKeyEnc(apiKeyEnc);
        user.setApiSecretEnc(apiSecretEnc);
        user.setAccountType(accountType);
        user.setTokenExpiryDays(tokenExpiryDays);

        return userRepository.save(user);
    }

    public String authenticate(String userId, String password) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (passwordEncoder.matches(password, user.getPasswordHash())) {
            String token = generateToken(user);
            saveAuthToken(user.getUserId(), token, user.getTokenExpiryDays());
            return token;
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    private String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUserId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + user.getTokenExpiryDays() * 86400000L))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    private void saveAuthToken(String userId, String token, int tokenExpiryDays) {
        AuthToken authToken = new AuthToken();
        authToken.setUserId(userId);
        authToken.setToken(token);
        authToken.setExpiryTime(LocalDateTime.now().plusDays(tokenExpiryDays));
        authTokenRepository.save(authToken);
    }

    public Optional<User> validateToken(String token) {
        return authTokenRepository.findByToken(token)
                .filter(authToken -> authToken.getExpiryTime().isAfter(LocalDateTime.now()))
                .flatMap(authToken -> userRepository.findByUserId(authToken.getUserId()));
    }
}
