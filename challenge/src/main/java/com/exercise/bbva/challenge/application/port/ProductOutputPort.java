package com.exercise.bbva.challenge.application.port;

import com.exercise.bbva.challenge.domain.BankProduct;

import java.util.List;
import java.util.Set;

public interface ProductOutputPort {
    List<BankProduct> getAllByIds(Set<Long> ids);

    BankProduct findByProduct(String product);

    BankProduct createProduct(BankProduct product);

    List<BankProduct> getAllProducts();
}
