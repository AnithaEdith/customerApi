package io.swagger.api;

import com.fasterxml.jackson.core.JsonParser;
import io.swagger.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-12T07:12:35.584Z")

@Controller
public class CustomerApiController implements CustomerApi {

    private static final Logger log = LoggerFactory.getLogger(CustomerApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    private CustomerService customerService;

    @org.springframework.beans.factory.annotation.Autowired
    public CustomerApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Customer> addCustomers(@ApiParam(value = "customer item to add"  )  @Valid @RequestBody Customer customer) {
        log.info(" inside addCustomers" + customer.getName());
        String accept = request.getHeader("Accept");
        customerService.addcustomer(customer);
        log.info(" inside addCustomers" + customer.getName());
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }

    public ResponseEntity<Void> deletesCustomers(@ApiParam(value = "customer item to add"  )  @Valid @RequestBody Customer customer) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Customer>> searchCustomers(@ApiParam(value = "pass an optional search string for looking up customers") @Valid @RequestParam(value = "searchString", required = false) String searchString,@Min(0)@ApiParam(value = "number of records to skip for pagination") @Valid @RequestParam(value = "skip", required = false) Integer skip,@Min(0) @Max(50) @ApiParam(value = "maximum number of records to return") @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
           try{
                final List<Customer> customerResults =customerService.listcustomers();
                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
                log.info("retrieved list of customers " + customerResults.size() + customerResults);
                //final String customerResults = "[ {  \"zipcode\" : 123451.0,  \"address\" : \"Address 1\",  \"contactnumber\" : 123.0,  \"city\" : \"Cuennai\",  \"name\" : \"Customer 1\",  \"id\" : \"d290f1ee-6c54-4b01-90e6-d701748f0851\",  \"state\" : \"Tamil Nadu\",  \"email\" : \"abc@def.com\"}, {  \"zipcode\" : 123451.0,  \"address\" : \"Address 1\",  \"contactnumber\" : 123.0,  \"city\" : \"Cuennai\",  \"name\" : \"Customer 1\",  \"id\" : \"d290f1ee-6c54-4b01-90e6-d701748f0851\",  \"state\" : \"Tamil Nadu\",  \"email\" : \"abc@def.com\"} ]";
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
        Customer customer=new Customer();
        if (accept != null && accept.contains("application/json")) {
            if(!isNullorZero(id) || !isNullorZero(zipcode) ){
                log.info("inside customersearchbyQueryParam2");
                if (!isNullorZero(id)) {
                    log.info("id value is " + id);

                    customer = customerService.listcustomerbyid(id);

                }
                if (!isNullorZero(zipcode)) {
                    log.info("zip value is " + zipcode);
                    customer = customerService.listcustomerbyZipCode(zipcode.intValue());

                }
                return new ResponseEntity<Customer>(customer,HttpStatus.OK);
            }
        }
        return new ResponseEntity<Customer>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static boolean isNullorZero(Integer i){
        return 0 == ( i == null ? 0 : i);
    }
}
