package com.exercise.bbva.challenge.application.service;

public interface AuthService {
    String extractUsername(String token);
    String generateToken(String username);

}
