package io.swagger.api;

import io.swagger.model.Customer;
import io.swagger.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    public Customer listcustomerbyZipCode(int zipcode) {
        log.info(" inside listcustomerbyid" );
        Customer customer = customerRepository.findByZipcode(zipcode);
        log.info(" customers.size" + customer.getName() );
        return customer;
    }

    public Customer listcustomerbyid(int customerid) {
        log.info(" inside listcustomerbyid" );
        Customer customer = customerRepository.getOne(customerid);
        log.info(" customers.size" + customer.getName() );
        return customer;
    }

    public List<Customer> listcustomers() {
        log.info(" inside listcustomers" );
        List<Customer> customers = customerRepository.findAll();
        log.info(" customers.size" + customers.size() );

        return customers;
    }

    public Customer addcustomer(Customer customer) {
        log.info(" inside addcustomer" + customer.getName());
        customerRepository.save(customer);
        log.info(" savedcustomer" + customer.getName());
        return customer;
    }


}
