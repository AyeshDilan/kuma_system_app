package edu.iset.service.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.iset.dao.CustomerEntity;
import edu.iset.dto.Customer;
import edu.iset.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;
    private CustomerEntity customerEntity;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);

        customer = Customer.builder()
                .firstName("test")
                .lastName("test")
                .shopName("test")
                .idNumber("111111111v")
                .address("test")
                .contact1("0711111111")
                .contact2("0722222222")
                .email("test@gmail.com")
                .identificationCode("test321")
                .build();

        customerEntity = new CustomerEntity();
        customerEntity.setId(1L);
        customerEntity.setFirstName("test");
        customerEntity.setLastName("test");
        customerEntity.setShopName("test");
        customerEntity.setIdNumber("111111111v");
        customerEntity.setAddress("test");
        customerEntity.setContact1("0711111111");
        customerEntity.setContact2("0722222222");
        customerEntity.setEmail("test@gmail.com");
        customerEntity.setIdentificationCode("test321");
    }

    @Test
    void registerCustomerTest(){
        when(objectMapper.convertValue(any(Customer.class), eq(CustomerEntity.class)))
                .thenReturn(customerEntity);

        customerService.registerCustomer(customer);

        verify(customerRepository, times(1)).save(customerEntity);
    }

    @Test
    void retriveAllCustomer(){
        List<CustomerEntity> entityList = new ArrayList<>();
        entityList.add(customerEntity);

        when(customerRepository.findAll()).thenReturn(entityList);
        when(objectMapper.convertValue(any(CustomerEntity.class),eq(Customer.class)))
                .thenReturn(customer);

        List<Customer> result = customerService.retriveAllCustomer();

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("test", result.get(0).getFirstName());
    }
}
