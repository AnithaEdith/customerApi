package io.swagger.api;

import io.swagger.Swagger2SpringBoot;
import io.swagger.model.Customer;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RunWith( SpringRunner.class )
@SpringBootTest( classes = Swagger2SpringBoot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties" )
@FixMethodOrder( MethodSorters.NAME_ASCENDING )
public class ITCustomerApiControllerTest {

    private static final Logger log = LoggerFactory.getLogger(ITCustomerApiControllerTest.class);
    private static int customerId1 = 1;
    private static int customerId2 = 2;
    @LocalServerPort
    private int port;
    private TestRestTemplate restTemplate = new TestRestTemplate();
    @Value( "${url.customerByZipCodeuri}" )
    private String customerByZipCodeQuery;
    @Value( "${url.customerApi}" )
    private String customerApi;

    @Value( "${serverhost}" )
    private String serverhost;

    @Autowired
    private CustomerService customerService;

    @Value( "${url.deleteCustomers}" )
    private String deleteCustomers;

    private HttpHeaders headers = new HttpHeaders();

    @Test
    public void addCustomerTest() {
        headers.add("Accept", "application/json");

        Customer customerOne = new Customer(1, "C1", 912, "c1@gmail.com", "addr1", "state1", "c1", 12345);
        Customer customerTwo = new Customer(2, "C2", 912, "c2@gmail.com", "addr2", "state2", "c2", 123);

        HttpEntity<Customer> entity = new HttpEntity<>(customerOne, headers);
        ResponseEntity<Customer> response1 = restTemplate.exchange(
                createURLWithPort(customerApi),
                HttpMethod.POST, entity, Customer.class);

        HttpEntity<Customer> entity2 = new HttpEntity<>(customerTwo, headers);
        ResponseEntity<Customer> response2 = restTemplate.exchange(
                createURLWithPort(customerApi),
                HttpMethod.POST, entity2, Customer.class);

        Customer responseBody = response1.getBody();
        Customer responseBody2 = response2.getBody();
        String actual = responseBody.getName();

        String expected = "C1";
        assertNotNull(response1.getBody().toString());
        Assert.assertEquals(expected, actual);
        customerId1 = responseBody.getId();
        customerId2 = responseBody2.getId();
        Assert.assertEquals(response1.getStatusCode(), HttpStatus.CREATED);
        Assert.assertEquals(response2.getStatusCode(), HttpStatus.CREATED);
        log.info("customer1 " + responseBody);
        log.info("customer2 " + responseBody2);
        log.info("customerId1 " + customerId1);
        log.info("customerId2 " + customerId2);

    }

    @Test
    public void customersearchByZipCodeTest() {
        headers.add("Accept", "application/json");
        String searchcustomerByZipCodeQuery = createURLWithPort(customerByZipCodeQuery);
        log.info(searchcustomerByZipCodeQuery);
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(searchcustomerByZipCodeQuery)
                .queryParam("zipcode", 12345);

        ResponseEntity<Customer> responseEntity = restTemplate.getForEntity(builder.toUriString(),
                Customer.class);
        Customer customer = responseEntity.getBody();
        Assert.assertEquals(12345, customer.getZipcode());

        log.info("actual is" + customer);
    }

    @Test
    public void customerSearchByIdTest() {
        headers.add("Accept", "application/json");
        log.info("customerId2 " + customerId2);
        ResponseEntity<Customer> responseEntity = searchPostUpdate(customerId2);
        Customer customer = responseEntity.getBody();
        Assert.assertEquals("C2", customer.getName());
        log.info("actual is" + customer);
    }

    @Test
    public void listallCustomersTest() {
        headers.add("Accept", "application/json");
        ResponseEntity<Customer[]> responseEntity = restTemplate.getForEntity(createURLWithPort(customerApi),
                Customer[].class);
        List<Customer> customers = Arrays.asList(responseEntity.getBody());
        log.info("actual is" + customers);
        Assert.assertEquals(2, customers.size());
    }

    @Test
    public void updateCustomerTest() {
        headers.add("Accept", "application/json");

        Customer customerOne = new Customer(customerId1, "C1.1", 912, "c1@gmail.com", "addr1", "state1", "c1", 12345);
        HttpEntity<Customer> entity = new HttpEntity<>(customerOne, headers);
        ResponseEntity<Customer> response = restTemplate.exchange(
                createURLWithPort(customerApi),
                HttpMethod.PUT, entity, Customer.class);

        Customer responseBody = response.getBody();
        String actual = responseBody.getName();

        String expected = "C1.1";
        assertNotNull(response.getBody().toString());
        log.info("updated value from response" + actual);
        Assert.assertEquals(expected, actual);

        ResponseEntity<Customer> responseEntity = searchPostUpdate(customerId1);
        Customer updatedResponse = responseEntity.getBody();

        Assert.assertEquals(expected, updatedResponse.getName());
        log.info("updated value from search" + updatedResponse.getName());
        log.info(response.getHeaders().toString());
    }

    private ResponseEntity<Customer> searchPostUpdate(int i) {
        String searchcustomerByIdQuery = createURLWithPort(customerByZipCodeQuery);

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(searchcustomerByIdQuery)
                .queryParam("id", i);

        return restTemplate.getForEntity(builder.toUriString(),
                Customer.class);
    }

    @Test
    public void z1deleteCustomerTest() {
        headers.add("Accept", "application/json");

        Customer customer = new Customer(customerId1, "C1.1", 912, "anc@gmail.com", "addr1", "state1", "c", 12354);
        HttpEntity<Customer> entity = new HttpEntity<>(customer, headers);
        ResponseEntity<Void> response = restTemplate.exchange(
                createURLWithPort(customerApi),
                HttpMethod.DELETE, entity, Void.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Customer> responseEntity = searchPostUpdate(customerId1);
        Assert.assertNull(responseEntity.getBody());
    }

    @Test
    public void z1deleteCustomerByIdTest() {
        headers.add("Accept", "application/json");

        Map<String, Integer> params = new HashMap<>();
        params.put("customerId", customerId2);

        String urlWithPort = createURLWithPort(customerApi + "/{customerId}");
        restTemplate.delete(urlWithPort, params);

        ResponseEntity<Customer> responseEntity = searchPostUpdate(customerId2);
        Assert.assertNull(responseEntity.getBody());
    }

    //@AfterClass
    @Test
    public void zdeleteAllCustomers() {
        String urlWithPort = createURLWithPort(deleteCustomers);
        restTemplate.delete(urlWithPort);
    }

    private String createURLWithPort(String uri) {
        return serverhost + port + uri;
    }
}