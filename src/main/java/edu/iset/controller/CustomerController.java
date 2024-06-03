package edu.iset.controller;

import edu.iset.dto.Customer;
import edu.iset.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    //save customer
    @PostMapping
    public void customerRegistration(@RequestBody Customer customer){
        customerService.registerCustomer(customer);
    }

    //get all customer
    @GetMapping
    List<Customer> retriveAllCustomer(){
        return customerService.retriveAllCustomer();
    }

}
