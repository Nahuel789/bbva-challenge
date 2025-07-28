package com.exercise.bbva.challenge.infrastructure.controller.impl;

import com.exercise.bbva.challenge.application.service.CustomerService;
import com.exercise.bbva.challenge.domain.exception.CustomerNotFoundException;
import com.exercise.bbva.challenge.infrastructure.dto.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    @Test
    void shouldCreate_withValidDTO_returnCustomerCreated() {
        Mockito.when(customerService.createCustomer(Mockito.any(CustomerDTO.class)))
                .thenReturn(getCustomerDTO());

        ResponseEntity<CustomerDTO> responseEntity = customerController.create(getCustomerDTO());
        assertNotNull(responseEntity);
        assertEquals(201, responseEntity.getStatusCode().value());
    }

    @Test
    void shouldGetAll_withoutError_returnAllList() {
        Mockito.when(customerService.getAll())
                .thenReturn(List.of(getCustomerDTO()));

        ResponseEntity<List<CustomerDTO>> response = customerController.getAll();

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void shouldGetById_withValidCustomerId_returnDTO() throws CustomerNotFoundException {
        Long customerId = 1234L;

        Mockito.when(customerService.findById(customerId))
                .thenReturn(getCustomerDTO());

        ResponseEntity<CustomerDTO> response = customerController.getById(customerId);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(customerId, response.getBody().getId());
    }

    @Test
    void updateCustomer_withValidInput_return204() throws CustomerNotFoundException {
        Long customerId = 1234L;
        CustomerDTO customerDTO = getCustomerDTO();

        Mockito.doNothing().when(customerService).update(customerId, customerDTO);

        ResponseEntity<Void> response = customerController.update(customerId, customerDTO);

        assertEquals(204, response.getStatusCode().value());
    }

    @Test
    void updatePhone_shouldReturnNoContentStatus() throws CustomerNotFoundException {
        Long customerId = 1234L;
        String phone = "5678";

        Mockito.doNothing().when(customerService).updatePhone(customerId, phone);

        ResponseEntity<Void> response = customerController.updatePhone(customerId, phone);

        assertEquals(204, response.getStatusCode().value());
    }

    @Test
    void shouldGetCustomersByProduct_withValidProduct_returnList() {
        String product = "TJCREDITO";

        Mockito.when(customerService.findByProduct(product))
                .thenReturn(List.of(getCustomerDTO()));

        ResponseEntity<List<CustomerDTO>> response = customerController.getCustomersByProduct(product);

        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertFalse(response.getBody().isEmpty());
    }

    @Test
    void delete_withValidCustomerId_returnSuccess204() throws CustomerNotFoundException {
        Long customerId = 1234L;

        Mockito.doNothing().when(customerService).delete(customerId);
        ResponseEntity<Void> response = customerController.delete(customerId);
        assertEquals(204, response.getStatusCode().value());
    }

    private static CustomerDTO getCustomerDTO() {
        return CustomerDTO.builder().id(1234L).phone("1234").mobilePhone("1234").number(11234).lastName("doe").firstName("john").bankProducts(Set.of("TJCREDITO","TJDEBITO")).build();
    }
}