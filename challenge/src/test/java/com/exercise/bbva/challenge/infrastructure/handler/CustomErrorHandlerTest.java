package com.exercise.bbva.challenge.infrastructure.handler;

import com.exercise.bbva.challenge.domain.exception.CustomerNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomErrorHandlerTest {

    CustomErrorHandler customErrorHandler = new CustomErrorHandler();

    @Test
    void handleResourceNotFound() {
        ResponseEntity<String> responseEntity = customErrorHandler.handleResourceNotFound(new CustomerNotFoundException(123L));
        assertNotNull(responseEntity);
        assertEquals(404, responseEntity.getStatusCode().value());
    }
}