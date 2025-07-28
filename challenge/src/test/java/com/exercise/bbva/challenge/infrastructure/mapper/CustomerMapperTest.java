package com.exercise.bbva.challenge.infrastructure.mapper;

import com.exercise.bbva.challenge.domain.BankProduct;
import com.exercise.bbva.challenge.domain.Customer;
import com.exercise.bbva.challenge.infrastructure.dto.CustomerDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.exercise.bbva.challenge.util.TestUtil.getCustomer;
import static com.exercise.bbva.challenge.util.TestUtil.getCustomerDTO;
import static com.exercise.bbva.challenge.util.TestUtil.getProducts;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CustomerMapperTest {

    @Test
    void mapDTOToCustomer_withValidData_returnCustomer() {
        CustomerDTO dto = getCustomerDTO();
        List<BankProduct> products = getProducts();

        Customer customer = CustomerMapper.map(dto, products);

        assertEquals(dto.getId(), customer.getId());
        assertEquals(dto.getFirstName(), customer.getFirstName());
        assertEquals(2, customer.getBankProducts().size());
        assertEquals("TJCREDITO", customer.getBankProducts().get(0).getProduct());
    }

    @Test
    void mapCustomerToDTO_withValidData_returnDTO() {
        List<BankProduct> products = getProducts();

        Customer customer = getCustomer(products);

        CustomerDTO dto = CustomerMapper.map(customer);

        assertEquals(customer.getId(), dto.getId());
        assertEquals(customer.getFirstName(), dto.getFirstName());
        assertEquals(2, dto.getBankProducts().size());
        assertTrue(dto.getBankProducts().contains("TJCREDITO"));
        assertTrue(dto.getBankProducts().contains("TJDEBITO"));
    }
}
