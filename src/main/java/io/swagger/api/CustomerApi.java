/**
 * NOTE: This class is auto generated by the swagger code generator program (1.0.11).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.annotations.*;
import io.swagger.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@javax.annotation.Generated( value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-12T07:12:35.584Z" )

@Api( value = "customer", description = "the customer API" )
public interface CustomerApi {

    @ApiOperation( value = "adds a new customer", nickname = "addCustomers", notes = "Adds a customer to the system", tags = {"developers",} )
    @ApiResponses( value = {
            @ApiResponse( code = 201, message = "customer created" ),
            @ApiResponse( code = 400, message = "invalid input, object invalid" ),
            @ApiResponse( code = 409, message = "an existing customer already exists" )} )
    @RequestMapping( value = "/customer",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST )
    ResponseEntity<Customer> addCustomers(@ApiParam( value = "customer item to add" ) @Valid @RequestBody Customer customer);


    @ApiOperation( value = "deletes a new customer", nickname = "deletesCustomers", notes = "deletes a customer to the system", tags = {"developers",} )
    @ApiResponses( value = {
            @ApiResponse( code = 201, message = "customer deleted" ),
            @ApiResponse( code = 400, message = "invalid input, object invalid" ),
            @ApiResponse( code = 409, message = "an existing customer already exists" )} )
    @RequestMapping( value = "/customer",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.DELETE )
    ResponseEntity<Void> deletesCustomers(@ApiParam( value = "customer item to add" ) @Valid @RequestBody Customer customer);


    @ApiOperation( value = "searches customers", nickname = "searchCustomers", notes = "By passing in the appropriate options, you can search for available customers in the system ", response = Customer.class, responseContainer = "List", tags = {"developers",} )
    @ApiResponses( value = {
            @ApiResponse( code = 200, message = "search results matching criteria", response = Customer.class, responseContainer = "List" ),
            @ApiResponse( code = 400, message = "bad input parameter" )} )
    @RequestMapping( value = "/customer",
            produces = {"application/json"},
            method = RequestMethod.GET )
    ResponseEntity<List<Customer>> searchCustomers(@ApiParam( value = "pass an optional search string for looking up customers" ) @Valid @RequestParam( value = "searchString", required = false ) String searchString, @Min( 0 ) @ApiParam( value = "number of records to skip for pagination" ) @Valid @RequestParam( value = "skip", required = false ) Integer skip, @Min( 0 ) @Max( 50 ) @ApiParam( value = "maximum number of records to return" ) @Valid @RequestParam( value = "limit", required = false ) Integer limit);


    @RequestMapping( value = "/customersearch", produces = {"application/json"}, method = RequestMethod.GET )
    ResponseEntity<Customer> customersearchbyQueryParam(@RequestParam( value = "id", required = false, defaultValue = "0" ) Integer id, @RequestParam( value = "zipcode", required = false, defaultValue = "0" ) Integer zipcode);

    @RequestMapping( value = "/customer",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT )
    ResponseEntity<Customer> updatesCustomers(@ApiParam( value = "customer item to add" ) @Valid @RequestBody Customer customer);


    @RequestMapping( value = "/customer/{customerId}",
            method = RequestMethod.DELETE )
    ResponseEntity<Void> deletesCustomerById(@ApiParam( value = "customer item to delete with Id" ) @PathVariable( "customerId" ) int customerId);

}
