package io.swagger.api;

import io.swagger.api.CustomerService;
import io.swagger.model.Customer;
import io.swagger.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class customerServiceTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;
    private Customer customer = new Customer(1, "Anitha", 912, "anc@gmail.com", "addr1", "state1", "c", 12354);

    @Configuration
    static class AccountServiceTestContextConfiguration {
        @Bean
        public CustomerService customerService() {
            return new CustomerService();
        }
        @Bean
        public CustomerRepository customerRepository() {
            return Mockito.mock(CustomerRepository.class);
        }
    }

    @Before
    public void setup() {
        List<Customer> customers=new ArrayList<>();
        customers.add(customer);
        Mockito.when(customerRepository.findAll()).thenReturn(customers);
        Mockito.when(customerRepository.getOne(Mockito.anyInt())).thenReturn(customer);
        Mockito.when(customerRepository.findByZipcode(Mockito.anyInt())).thenReturn(customer);
    }

    @Test
    public void addcustomerTest() {
        Customer addedcustomer=customerService.addcustomer(customer);
        logger.info(addedcustomer.getName());
        assertNotNull( addedcustomer);
    }

    @Test
    public void listcustomersTest() {
        List<Customer> listedcustomers=customerService.listcustomers();
        logger.info("size" + String.valueOf(listedcustomers.size()));
        assertNotNull( listedcustomers);
    }

    @Test
    public void listcustomerbyidTest() {
        Customer customers=customerService.listcustomerbyid(1);
        logger.info("customers" + customers.getName());
        assertNotNull( customers);
    }

    @Test
    public void listcustomerbyZipCodeTest() {
        Customer customers=customerService.listcustomerbyZipCode(12354);
        logger.info("customers" + customers.getName());
        assertNotNull( customers);
    }

}


