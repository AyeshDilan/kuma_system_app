package edu.iset.service.customer;

import edu.iset.dto.Customer;

import java.util.List;

public interface CustomerService {
    void registerCustomer(Customer customer);

    List<Customer> retriveAllCustomer();
}
