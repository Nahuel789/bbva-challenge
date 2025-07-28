package com.exercise.bbva.challenge.application.port;

import com.exercise.bbva.challenge.domain.Customer;
import com.exercise.bbva.challenge.domain.exception.CustomerNotFoundException;

import java.util.List;

public interface CustomerOutputPort {

    Customer saveCustomer(Customer customer);

    void deleteCustomer(Long customerId);

    List<Customer> getCustomersByProduct(String product);

    Customer getCustomerById(Long customerId) throws CustomerNotFoundException;

    List<Customer> getAllCustomers();
}
