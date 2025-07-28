package com.exercise.bbva.challenge.infrastructure.controller.impl;

import com.exercise.bbva.challenge.application.service.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    AuthService authService;

    @InjectMocks
    AuthController authController;

    @Test
    void shouldGetToken_withValidUsername_returnToken() {
        Mockito.when(authService.generateToken(Mockito.anyString()))
                .thenReturn("valid-token");
        ResponseEntity<String> response = authController.getToken("username");
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(200, response.getStatusCode().value());
    }
}