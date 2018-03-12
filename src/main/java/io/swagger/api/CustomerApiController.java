package io.swagger.api;

import io.swagger.model.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

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

    @org.springframework.beans.factory.annotation.Autowired
    public CustomerApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addCustomers(@ApiParam(value = "customer item to add"  )  @Valid @RequestBody Customer customer) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deletesCustomers(@ApiParam(value = "customer item to add"  )  @Valid @RequestBody Customer customer) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Customer>> searchCustomers(@ApiParam(value = "pass an optional search string for looking up customers") @Valid @RequestParam(value = "searchString", required = false) String searchString,@Min(0)@ApiParam(value = "number of records to skip for pagination") @Valid @RequestParam(value = "skip", required = false) Integer skip,@Min(0) @Max(50) @ApiParam(value = "maximum number of records to return") @Valid @RequestParam(value = "limit", required = false) Integer limit) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Customer>>(objectMapper.readValue("[ {  \"zipcode\" : 123451.0,  \"address\" : \"Address 1\",  \"contactnumber\" : 123.0,  \"city\" : \"Cuennai\",  \"name\" : \"Customer 1\",  \"id\" : \"d290f1ee-6c54-4b01-90e6-d701748f0851\",  \"state\" : \"Tamil Nadu\",  \"email\" : \"abc@def.com\"}, {  \"zipcode\" : 123451.0,  \"address\" : \"Address 1\",  \"contactnumber\" : 123.0,  \"city\" : \"Cuennai\",  \"name\" : \"Customer 1\",  \"id\" : \"d290f1ee-6c54-4b01-90e6-d701748f0851\",  \"state\" : \"Tamil Nadu\",  \"email\" : \"abc@def.com\"} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Customer>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Customer>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
