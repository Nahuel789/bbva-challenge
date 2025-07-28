package com.exercise.bbva.challenge.application.service.impl;

import com.exercise.bbva.challenge.application.port.ProductOutputPort;
import com.exercise.bbva.challenge.application.service.ProductService;
import com.exercise.bbva.challenge.domain.BankProduct;
import com.exercise.bbva.challenge.infrastructure.dto.ProductDTO;
import com.exercise.bbva.challenge.infrastructure.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductOutputPort productOutputPort;

    public ProductServiceImpl(ProductOutputPort productOutputPort) {
        this.productOutputPort = productOutputPort;
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        BankProduct bankProduct = ProductMapper.map(productDTO);
        return ProductMapper.map(productOutputPort.createProduct(bankProduct));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productOutputPort.getAllProducts().stream().map(ProductMapper::map).toList();
    }
}
