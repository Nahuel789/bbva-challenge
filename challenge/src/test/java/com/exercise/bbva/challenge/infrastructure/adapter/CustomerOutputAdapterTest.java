package com.exercise.bbva.challenge.infrastructure.adapter;

import com.exercise.bbva.challenge.domain.Customer;
import com.exercise.bbva.challenge.domain.exception.CustomerNotFoundException;
import com.exercise.bbva.challenge.infrastructure.adapter.repository.CustomerJPARepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.exercise.bbva.challenge.util.TestUtil.getCustomer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerOutputAdapterTest {

    @Mock
    CustomerJPARepository customerJpaRepository;

    @InjectMocks
    CustomerOutputAdapter adapter;

    @Test
    void saveCustomer_withValidCustomer_returnCustomerCreated() {
        Customer customer = getCustomer();

        when(customerJpaRepository.save(customer)).thenReturn(customer);

        Customer result = adapter.saveCustomer(customer);

        assertNotNull(result);
        assertEquals(customer.getId(), result.getId());
    }

    @Test
    void deleteCustomer_withValidId_returnVoid() {
        Long customerId = 1L;

        doNothing().when(customerJpaRepository).deleteById(customerId);

        adapter.deleteCustomer(customerId);

        verify(customerJpaRepository, times(1)).deleteById(customerId);
    }

    @Test
    void shouldGetCustomersByProduct_withValidProduct_returnList() {
        String product = "TJDEBITO";
        List<Customer> customers = List.of(getCustomer());

        when(customerJpaRepository.findAllByProduct(product)).thenReturn(customers);

        List<Customer> result = adapter.getCustomersByProduct(product);

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }

    @Test
    void getCustomerById_withValidId_returnCustomer() throws CustomerNotFoundException {
        Long id = 1L;
        Customer customer = getCustomer();

        when(customerJpaRepository.findById(id)).thenReturn(Optional.of(customer));

        Customer result = adapter.getCustomerById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void getCustomerById_withIdInvalid_throwCustomerNotFound() {
        Long id = 99L;

        when(customerJpaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> adapter.getCustomerById(id));
    }

    @Test
    void getAllCustomers_withData_returnList() {
        List<Customer> customers = List.of(getCustomer());

        when(customerJpaRepository.findAll()).thenReturn(customers);

        List<Customer> result = adapter.getAllCustomers();

        assertEquals(1, result.size());
    }
}