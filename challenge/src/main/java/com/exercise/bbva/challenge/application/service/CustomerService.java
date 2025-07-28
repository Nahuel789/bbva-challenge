package com.exercise.bbva.challenge.application.service;

import com.exercise.bbva.challenge.domain.exception.CustomerNotFoundException;
import com.exercise.bbva.challenge.infrastructure.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO dto);

    List<CustomerDTO> getAll();

    void updatePhone(Long id, String phone) throws CustomerNotFoundException;

    List<CustomerDTO> findByProduct(String product);

    void delete(Long id) throws CustomerNotFoundException;

    CustomerDTO findById(Long id) throws CustomerNotFoundException;

    void update(Long id, CustomerDTO dto) throws CustomerNotFoundException;
}
