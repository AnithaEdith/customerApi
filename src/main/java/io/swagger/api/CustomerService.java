package io.swagger.api;

import io.swagger.model.Customer;
import io.swagger.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Component
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    public Customer listcustomerbyZipCode(int zipcode) {
        log.info(" inside listcustomerbyZipCode");
        Customer customer = customerRepository.findByZipcode(zipcode);
        log.info(" customers.size" + customer.getName());
        return customer;
    }

    public Customer listcustomerbyid(int customerid) {
        log.info(" inside listcustomerbyid");
        Customer customer;
        try {
            customer = customerRepository.getOne(customerid);
            if (customer != null && !customer.equals(null)) {
                log.info(" customers.size" + customer.getName());
            }
        } catch (EntityNotFoundException entityNotFoundException) {
            log.info(" EntityNotFoundException exception");
            return null;
        }
        log.info(" end of listcustomerbyid");
        return customer;
    }

    public List<Customer> listcustomers() {
        log.info(" inside listcustomers");
        List<Customer> customers = customerRepository.findAll();
        log.info(" customers.size" + customers.size());

        return customers;
    }

    public Customer addcustomer(Customer customer) {
        log.info(" inside addcustomer" + customer.getName());
        Customer addedcustomer = customerRepository.save(customer);
        log.info(" savedcustomer" + customer.getName());
        return addedcustomer;
    }

    public void deleteCustomerById(int customerId) {
        log.info(" inside deletesCustomerById in CustomerService" + customerId);
        customerRepository.delete(customerId);
    }

    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer);
    }

    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }

}