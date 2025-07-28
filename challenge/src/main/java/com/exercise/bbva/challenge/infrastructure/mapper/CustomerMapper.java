package com.exercise.bbva.challenge.infrastructure.mapper;

import com.exercise.bbva.challenge.domain.BankProduct;
import com.exercise.bbva.challenge.domain.Customer;
import com.exercise.bbva.challenge.infrastructure.dto.CustomerDTO;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CustomerMapper {

    public static Customer map(CustomerDTO customerDTO, List<BankProduct> bankProducts) {
        return Customer.builder()
                .id(customerDTO.getId())
                .street(customerDTO.getStreet())
                .number(customerDTO.getNumber())
                .postalCode(customerDTO.getPostalCode())
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .phone(customerDTO.getPhone())
                .mobilePhone(customerDTO.getMobilePhone())
                .bankProducts(bankProducts)
                .build();
    }

    public static CustomerDTO map(Customer customer) {
        return CustomerDTO.builder()
                .id(customer.getId())
                .street(customer.getStreet())
                .number(customer.getNumber())
                .postalCode(customer.getPostalCode())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .phone(customer.getPhone())
                .mobilePhone(customer.getMobilePhone())
                .bankProducts(customer.getBankProducts().stream()
                        .map(BankProduct::getProduct)
                        .collect(java.util.stream.Collectors.toSet()))
                .build();
    }
}
