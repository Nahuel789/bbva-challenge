package com.exercise.bbva.challenge.infrastructure.adapter;

import com.exercise.bbva.challenge.application.port.CustomerOutputPort;
import com.exercise.bbva.challenge.domain.Customer;
import com.exercise.bbva.challenge.domain.exception.CustomerNotFoundException;
import com.exercise.bbva.challenge.infrastructure.adapter.repository.CustomerJPARepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerOutputAdapter implements CustomerOutputPort {

    private final CustomerJPARepository customerJpaRepository;

    public CustomerOutputAdapter(CustomerJPARepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerJpaRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerJpaRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> getCustomersByProduct(String product) {
        return customerJpaRepository.findAllByProduct(product);
    }

    @Override
    public Customer getCustomerById(Long customerId) throws CustomerNotFoundException {
        return customerJpaRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerJpaRepository.findAll();
    }
}
