package com.exercise.bbva.challenge.infrastructure.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductDTO {
    Long id;
    String product;
}
