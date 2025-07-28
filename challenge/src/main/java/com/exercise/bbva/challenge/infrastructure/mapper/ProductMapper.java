package com.exercise.bbva.challenge.infrastructure.mapper;

import com.exercise.bbva.challenge.domain.BankProduct;
import com.exercise.bbva.challenge.infrastructure.dto.ProductDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {

    public static BankProduct map(ProductDTO productDTO) {
        return BankProduct.builder()
                .product(productDTO.getProduct()).build();
    }

    public static ProductDTO map(BankProduct bankProduct) {
        return ProductDTO.builder()
                .product(bankProduct.getProduct()).id(bankProduct.getId()).build();
    }
}

