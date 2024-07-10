package com.algostrategix.alpacatrdeapi.service;

import com.algostrategix.alpacatrdeapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.algostrategix.alpacatrdeapi.model.User user = userRepository.findByUserId(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(user.getUserId(), user.getPasswordHash(), new ArrayList<>());
    }
}