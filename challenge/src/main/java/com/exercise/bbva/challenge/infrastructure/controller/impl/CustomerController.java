package com.exercise.bbva.challenge.infrastructure.controller.impl;

import com.exercise.bbva.challenge.application.service.CustomerService;
import com.exercise.bbva.challenge.domain.exception.CustomerNotFoundException;
import com.exercise.bbva.challenge.infrastructure.controller.CustomerApi;
import com.exercise.bbva.challenge.infrastructure.dto.CustomerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController implements CustomerApi {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public ResponseEntity<CustomerDTO> create(CustomerDTO dto) {
        return new ResponseEntity<>(customerService.createCustomer(dto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<CustomerDTO>> getAll() {
        return new ResponseEntity<>(customerService.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CustomerDTO> getById(Long customerId) throws CustomerNotFoundException {
        return new ResponseEntity<>(customerService.findById(customerId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> update(Long id, CustomerDTO dto) throws CustomerNotFoundException {
        customerService.update(id, dto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> updatePhone(Long id, String phone) throws CustomerNotFoundException {
        customerService.updatePhone(id, phone);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<CustomerDTO>> getCustomersByProduct(String product) {
        return new ResponseEntity<>(customerService.findByProduct(product), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) throws CustomerNotFoundException {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
