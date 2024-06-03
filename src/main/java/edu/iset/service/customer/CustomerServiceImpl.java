package edu.iset.service.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.iset.dao.CustomerEntity;
import edu.iset.dto.Customer;
import edu.iset.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    //Save registered customer in database
    @Override
    public void registerCustomer(Customer customer) {
        CustomerEntity customerEntity = objectMapper.convertValue(customer, CustomerEntity.class);
        customerRepository.save(customerEntity);
    }

    @Override
    public List<Customer> retriveAllCustomer() {
        List<Customer> list = new ArrayList<>();
        Iterable<CustomerEntity> customerEntityIterable = customerRepository.findAll();
        for (CustomerEntity entity : customerEntityIterable) {
            Customer customer = objectMapper.convertValue(entity, Customer.class);
            list.add(customer);
        }
        return list;
    }
}
