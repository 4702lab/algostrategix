package com.algostrategix.alpacatrdeapi.repository;

import com.algostrategix.alpacatrdeapi.model.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {
    Optional<AuthToken> findByToken(String token);
}