package com.algostrategix.alpacatrdeapi.repository;

import com.algostrategix.alpacatrdeapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
}