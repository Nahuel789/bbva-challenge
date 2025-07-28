package com.exercise.bbva.challenge.infrastructure.controller.impl;

import com.exercise.bbva.challenge.application.service.AuthService;
import com.exercise.bbva.challenge.infrastructure.controller.AuthApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public ResponseEntity<String> getToken(String username) {
        return ResponseEntity.ok(authService.generateToken(username));
    }
}
