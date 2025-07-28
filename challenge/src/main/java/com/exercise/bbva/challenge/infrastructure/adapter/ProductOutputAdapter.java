package com.exercise.bbva.challenge.infrastructure.adapter;

import com.exercise.bbva.challenge.application.port.ProductOutputPort;
import com.exercise.bbva.challenge.domain.BankProduct;
import com.exercise.bbva.challenge.infrastructure.adapter.repository.ProductJPARepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ProductOutputAdapter implements ProductOutputPort {

    private final ProductJPARepository productJPARepository;

    public ProductOutputAdapter(ProductJPARepository productJPARepository) {
        this.productJPARepository = productJPARepository;
    }

    @Override
    public List<BankProduct> getAllByIds(Set<Long> ids) {
        return productJPARepository.findAllById(ids);
    }

    @Override
    public BankProduct findByProduct(String product) {
        return productJPARepository.findByProduct(product);
    }

    @Override
    public BankProduct createProduct(BankProduct product) {
        return productJPARepository.save(product);
    }

    @Override
    public List<BankProduct> getAllProducts() {
        return productJPARepository.findAll();
    }
}
