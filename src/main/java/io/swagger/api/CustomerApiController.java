package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@javax.annotation.Generated( value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-12T07:12:35.584Z" )

@Controller
public class CustomerApiController implements CustomerApi {

    private static final Logger log = LoggerFactory.getLogger(CustomerApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private CustomerService customerService;

    @Autowired
    public CustomerApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public static boolean isNullorZero(Integer i) {
        return 0 == (i == null ? 0 : i);
    }

    public ResponseEntity<Customer> addCustomers(@ApiParam( value = "customer item to add" ) @Valid @RequestBody Customer customer) {
        log.info(" inside addCustomers" + customer.getName());
        String accept = request.getHeader("Accept");
        Customer addedcustomer = customerService.addcustomer(customer);
        log.info(" inside addCustomers" + customer.getName());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<Customer>(addedcustomer, HttpStatus.CREATED);
    }

    public ResponseEntity<Void> deletesCustomers(@ApiParam( value = "customer item to add" ) @Valid @RequestBody Customer customer) {
        String accept = request.getHeader("Accept");
        customerService.deleteCustomer(customer);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deletesCustomerById(@ApiParam( value = "customer item to add" ) @PathVariable( "customerId" ) int customerId) {
        log.info(" inside deletesCustomerById" + customerId);
        String accept = request.getHeader("Accept");
        customerService.deleteCustomerById(customerId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Void> deletesAllCustomers() {
        log.info(" inside deleteall");
        customerService.deleteAllCustomers();
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<List<Customer>> searchCustomers(@ApiParam( value = "pass an optional search string for looking up customers" ) @Valid @RequestParam( value = "searchString", required = false ) String searchString, @Min( 0 ) @ApiParam( value = "number of records to skip for pagination" ) @Valid @RequestParam( value = "skip", required = false ) Integer skip, @Min( 0 ) @Max( 50 ) @ApiParam( value = "maximum number of records to return" ) @Valid @RequestParam( value = "limit", required = false ) Integer limit) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                final List<Customer> customerResults = customerService.listcustomers();
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
                log.info("retrieved list of customers " + customerResults.size() + customerResults);
                return new ResponseEntity<List<Customer>>(customerResults, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<Customer> customersearchbyQueryParam(Integer id, Integer zipcode) {
        log.info("inside customersearchbyQueryParam");
        String accept = request.getHeader("Accept");
        Customer customer = new Customer();
        if (accept != null && accept.contains("application/json")) {
            if (!isNullorZero(id) || !isNullorZero(zipcode)) {
                log.info("inside customersearchbyQueryParam2");
                if (!isNullorZero(id)) {
                    log.info("id value is " + id);
                    customer = customerService.listcustomerbyid(id);
                } else if (!isNullorZero(zipcode)) {
                    log.info("zip value is " + zipcode);
                    customer = customerService.listcustomerbyZipCode(zipcode.intValue());
                } else {
                    return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
                }

                if (customer != null && !isEmpty(customer)) {
                    log.info("customer is not empty" + customer.getName());
                    return new ResponseEntity<Customer>(customer, HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Customer> updatesCustomers(@RequestBody Customer customer) {
        log.info(" inside updatesCustomers" + customer.getName());
        String accept = request.getHeader("Accept");
        customerService.addcustomer(customer);
        log.info(" inside updatesCustomers" + customer.getName());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
}
