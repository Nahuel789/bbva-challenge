package com.exercise.bbva.challenge.infrastructure.controller;

import com.exercise.bbva.challenge.domain.exception.CustomerNotFoundException;
import com.exercise.bbva.challenge.infrastructure.dto.CustomerDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/customers")
public interface CustomerApi {

    @PostMapping
    ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO dto);

    @GetMapping
    ResponseEntity<List<CustomerDTO>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<CustomerDTO> getById(@PathVariable("id") Long id) throws CustomerNotFoundException;

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody CustomerDTO dto) throws CustomerNotFoundException;

    @PatchMapping("/{id}/phone")
    ResponseEntity<Void> updatePhone(@PathVariable("id") Long id, @RequestBody String phone) throws CustomerNotFoundException;

    @GetMapping("/product/{product}")
    ResponseEntity<List<CustomerDTO>> getCustomersByProduct(@PathVariable("product") String product);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) throws CustomerNotFoundException;
}
