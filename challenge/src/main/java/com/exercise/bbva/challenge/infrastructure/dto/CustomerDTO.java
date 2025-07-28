package com.exercise.bbva.challenge.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class CustomerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String street;
    private Integer number;
    private Integer postalCode;
    private String phone;
    private String mobilePhone;
    private Set<String> bankProducts;
}
