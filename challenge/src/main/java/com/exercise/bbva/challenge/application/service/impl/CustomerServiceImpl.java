package com.exercise.bbva.challenge.application.service.impl;

import com.exercise.bbva.challenge.application.port.CustomerOutputPort;
import com.exercise.bbva.challenge.application.port.ProductOutputPort;
import com.exercise.bbva.challenge.application.service.CustomerService;
import com.exercise.bbva.challenge.domain.BankProduct;
import com.exercise.bbva.challenge.domain.Customer;
import com.exercise.bbva.challenge.domain.exception.CustomerNotFoundException;
import com.exercise.bbva.challenge.infrastructure.dto.CustomerDTO;
import com.exercise.bbva.challenge.infrastructure.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerOutputPort customerOutputPort;

    private final ProductOutputPort productOutputPort;

    public CustomerServiceImpl(CustomerOutputPort customerOutputPort, ProductOutputPort productOutputPort) {
        this.customerOutputPort = customerOutputPort;
        this.productOutputPort = productOutputPort;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO dto) {
        List<BankProduct> bankProducts = dto.getBankProducts().stream().map(productOutputPort::findByProduct).toList();
        Customer customer = CustomerMapper.map(dto, bankProducts);
        return CustomerMapper.map(customerOutputPort.saveCustomer(customer));
    }

    @Override
    public List<CustomerDTO> getAll() {
        return customerOutputPort.getAllCustomers().stream().map(CustomerMapper::map).toList();
    }

    @Override
    public void updatePhone(Long id, String phone) throws CustomerNotFoundException {
        Customer customer = customerOutputPort.getCustomerById(id);
        customer.setPhone(phone);
        customerOutputPort.saveCustomer(customer);
    }

    @Override
    public List<CustomerDTO> findByProduct(String product) {
        return customerOutputPort.getCustomersByProduct(product).stream().map(CustomerMapper::map).toList();
    }

    @Override
    public void delete(Long id) throws CustomerNotFoundException {
        if (customerOutputPort.getCustomerById(id) != null) customerOutputPort.deleteCustomer(id);
    }

    @Override
    public CustomerDTO findById(Long id) throws CustomerNotFoundException {
        return CustomerMapper.map(customerOutputPort.getCustomerById(id));
    }

    @Override
    public void update(Long id, CustomerDTO dto) throws CustomerNotFoundException {
        if (customerOutputPort.getCustomerById(id) != null) {
            List<BankProduct> bankProducts = dto.getBankProducts().stream().map(productOutputPort::findByProduct).toList();
            Customer customer = CustomerMapper.map(dto, bankProducts);
            customerOutputPort.saveCustomer(customer);
        }
    }
}
