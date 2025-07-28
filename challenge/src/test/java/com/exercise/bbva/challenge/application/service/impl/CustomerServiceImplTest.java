package com.exercise.bbva.challenge.application.service.impl;

import com.exercise.bbva.challenge.application.port.CustomerOutputPort;
import com.exercise.bbva.challenge.application.port.ProductOutputPort;
import com.exercise.bbva.challenge.domain.BankProduct;
import com.exercise.bbva.challenge.domain.Customer;
import com.exercise.bbva.challenge.domain.exception.CustomerNotFoundException;
import com.exercise.bbva.challenge.infrastructure.dto.CustomerDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static com.exercise.bbva.challenge.util.TestUtil.getCustomerDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerOutputPort customerOutputPort;

    @Mock
    private ProductOutputPort productOutputPort;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void createCustomer_withValidDTO_returnCustomerCreated() {
        CustomerDTO dto = getCustomerDTO();

        BankProduct mockProduct = BankProduct.builder().id(10L).product("Cuenta Corriente").build();
        Customer saved = Customer.builder().id(1L).firstName("Nahuel").lastName("Carbajal")
                .bankProducts(List.of(mockProduct)).build();

        when(productOutputPort.findByProduct("Cuenta Corriente")).thenReturn(mockProduct);
        when(customerOutputPort.saveCustomer(any())).thenReturn(saved);

        CustomerDTO result = customerService.createCustomer(dto);

        assertNotNull(result);
        assertEquals("Nahuel", result.getFirstName());
        verify(customerOutputPort).saveCustomer(any());
    }

    @Test
    void getAllCustomers_withValidData_returnList() {
        Customer customer = Customer.builder().id(1L).firstName("Test").bankProducts(List.of(BankProduct.builder().build())).lastName("User").build();
        when(customerOutputPort.getAllCustomers()).thenReturn(List.of(customer));

        List<CustomerDTO> result = customerService.getAll();

        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getFirstName());
    }

    @Test
    void updatePhone_withValidInput_returnVoid() throws CustomerNotFoundException {
        Customer customer = Customer.builder().id(1L).phone("123").build();
        when(customerOutputPort.getCustomerById(1L)).thenReturn(customer);

        customerService.updatePhone(1L, "456");

        verify(customerOutputPort).saveCustomer(customer);
        assertEquals("456", customer.getPhone());
    }

    @Test
    void findByProduct_withValidId_returnCustomer() {
        Customer customer = Customer.builder().id(1L).bankProducts(List.of(BankProduct.builder().product("Caja de Ahorro").build())).firstName("Ana").build();
        when(customerOutputPort.getCustomersByProduct("Caja de Ahorro")).thenReturn(List.of(customer));

        List<CustomerDTO> result = customerService.findByProduct("Caja de Ahorro");

        assertEquals(1, result.size());
        assertEquals("Ana", result.get(0).getFirstName());
    }

    @Test
    void deleteCustomer_withValidId_returnVoid() throws CustomerNotFoundException {
        Customer customer = Customer.builder().id(1L).build();
        when(customerOutputPort.getCustomerById(1L)).thenReturn(customer);

        customerService.delete(1L);

        verify(customerOutputPort).deleteCustomer(1L);
    }

    @Test
    void findCustomer_withValidId_returnCustomer() throws CustomerNotFoundException {
        Customer customer = Customer.builder().id(2L).bankProducts(List.of(BankProduct.builder().product("Caja de ahorro").build())).firstName("Pepe").build();
        when(customerOutputPort.getCustomerById(2L)).thenReturn(customer);

        CustomerDTO result = customerService.findById(2L);

        assertEquals("Pepe", result.getFirstName());
    }

    @Test
    void updateCustomer_withValidInput_updateSuccessfully() throws CustomerNotFoundException {
        CustomerDTO dto = CustomerDTO.builder()
                .id(1L)
                .firstName("Carlos")
                .bankProducts(Set.of("Plazo Fijo"))
                .build();

        BankProduct product = BankProduct.builder().id(10L).product("Plazo Fijo").build();
        Customer existing = Customer.builder().id(1L).build();

        when(customerOutputPort.getCustomerById(1L)).thenReturn(existing);
        when(productOutputPort.findByProduct("Plazo Fijo")).thenReturn(product);

        customerService.update(1L, dto);

        verify(customerOutputPort).saveCustomer(any());
    }

}