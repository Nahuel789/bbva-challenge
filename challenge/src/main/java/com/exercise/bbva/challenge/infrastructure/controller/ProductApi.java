package com.exercise.bbva.challenge.infrastructure.controller;

import com.exercise.bbva.challenge.infrastructure.dto.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/products")
public interface ProductApi {

    @PostMapping
    ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO);

    @GetMapping
    ResponseEntity<List<ProductDTO>> getAllProducts();
}
