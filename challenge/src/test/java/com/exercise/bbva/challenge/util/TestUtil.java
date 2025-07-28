package com.exercise.bbva.challenge.util;

import com.exercise.bbva.challenge.domain.BankProduct;
import com.exercise.bbva.challenge.domain.Customer;
import com.exercise.bbva.challenge.infrastructure.dto.CustomerDTO;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Set;

@UtilityClass
public class TestUtil {

    public static Customer getCustomer() {
        return Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .street("Fake St")
                .number(123)
                .postalCode(1000)
                .phone("123456")
                .mobilePhone("7891011")
                .bankProducts(List.of())
                .build();
    }

    public static CustomerDTO getCustomerDTO() {
        return CustomerDTO.builder()
                .id(1L)
                .firstName("Nahuel")
                .lastName("Carbajal")
                .bankProducts(Set.of("Cuenta Corriente"))
                .build();
    }

    public static BankProduct getProduct(Long id, String name) {
        return BankProduct.builder()
                .id(id)
                .product(name)
                .build();
    }

    public static List<BankProduct> getProducts() {
        return List.of(
                BankProduct.builder().id(1L).product("TJCREDITO").build(),
                BankProduct.builder().id(2L).product("TJDEBITO").build()
        );
    }

    public static Customer getCustomer(List<BankProduct> products) {
        return Customer.builder()
                .id(1234L)
                .firstName("John")
                .lastName("Doe")
                .street("Fake St")
                .number(100)
                .postalCode(1234)
                .phone("1234")
                .mobilePhone("5678")
                .bankProducts(products)
                .build();
    }
}
