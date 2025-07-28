package com.exercise.bbva.challenge.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/auth")
public interface AuthApi {
    @PostMapping("/token")
    ResponseEntity<String> getToken(@RequestParam String username);
}
