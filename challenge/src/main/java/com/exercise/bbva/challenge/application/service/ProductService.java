package com.exercise.bbva.challenge.application.service;

import com.exercise.bbva.challenge.infrastructure.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO product);

    List<ProductDTO> getAllProducts();
}
