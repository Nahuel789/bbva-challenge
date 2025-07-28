package com.exercise.bbva.challenge.application.service.impl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AuthServiceImplTest {
    private AuthServiceImpl authService;
    private final String secretKey = "12345678901234567890123456789012";

    @BeforeEach
    void setUp() {
        authService = new AuthServiceImpl(secretKey);
    }

    @Test
    void generateAndExtract_withValidData_returnSuccessfullyResponse() {
        String username = "testuser";
        String token = authService.generateToken(username);

        assertNotNull(token);
        String extracted = authService.extractUsername(token);
        assertEquals(username, extracted);
    }

    @Test
    void extractUsername_withExpiredToken_returnException() {
        String token = generateTokenCustom();
        assertThrows(ExpiredJwtException.class, () -> authService.extractUsername(token));
    }

    private String generateTokenCustom() {
        return Jwts.builder()
                .setSubject("username")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }

    @Test
    void createToken_withDifferentUsers_returnDifferentTokens() {
        String token1 = authService.generateToken("user1");
        String token2 = authService.generateToken("user2");

        assertNotEquals(token1, token2);
    }

    @Test
    void generateTokenAndExtract_withSameUser_returnSameName() {
        String username = "nahuel";
        String token = authService.generateToken(username);

        String extracted = authService.extractUsername(token);
        assertEquals(username, extracted);
    }
}