package com.exercise.bbva.challenge.infrastructure.controller.impl;

import com.exercise.bbva.challenge.application.service.ProductService;
import com.exercise.bbva.challenge.infrastructure.controller.ProductApi;
import com.exercise.bbva.challenge.infrastructure.dto.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController implements ProductApi {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    public ResponseEntity<ProductDTO> createProduct(ProductDTO productDTO) {
        return ResponseEntity.status(201).body(productService.createProduct(productDTO));
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}
