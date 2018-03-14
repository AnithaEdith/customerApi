package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.Swagger2SpringBoot;
import io.swagger.model.Customer;
import io.swagger.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;


@RunWith( SpringRunner.class )
@SpringBootTest( classes = Swagger2SpringBoot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties" )
public class ITCustomerApiControllerTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    @MockBean
    private CustomerRepository customerRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    private HttpHeaders headers = new HttpHeaders();
    private static final Logger log = LoggerFactory.getLogger(ITCustomerApiControllerTest.class);

    @Before
    public void setup() {
        Customer customer = new Customer(1, "Anitha", 912, "anc@gmail.com", "addr1", "state1", "c", 12354);
        Customer updatecustomer = new Customer(1, "Anitha1", 912, "anc1@gmail.com", "addr2", "state2", "c", 12354);
        given(this.customerService.
                listcustomerbyid(1)
        ).willReturn(customer);

        given(this.customerService.
                listcustomerbyZipCode(12345)
        ).willReturn(customer);

        given(this.customerService.
                addcustomer(updatecustomer)
        ).willReturn(updatecustomer);

        given(this.customerService.
                addcustomer(updatecustomer)
        ).willReturn(updatecustomer);

        Mockito.doNothing().when(this.customerService).deleteCustomer(customer);
    }


    @Test
    public void testSearchCustomerByZipCode() {

        headers.add("Accept", "application/json");
        String searchcustomerByZipCodeQuery = createURLWithPort("/AnithaEdith/CustomerAPI/1.0.0/customersearch");

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(searchcustomerByZipCodeQuery)
                .queryParam("zipcode", 12345);

        ResponseEntity<Customer> responseEntity = restTemplate.getForEntity(builder.toUriString(),
                Customer.class);
        Customer customer = responseEntity.getBody();
        log.info("actual is" + customer);
    }

    @Test
    public void testSearchCustomerById() {
        headers.add("Accept", "application/json");
        String searchcustomerByIdQuery = createURLWithPort("/AnithaEdith/CustomerAPI/1.0.0/customersearch");

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(searchcustomerByIdQuery)
                .queryParam("id", 1);

        ResponseEntity<Customer> responseEntity = restTemplate.getForEntity(builder.toUriString(),
                Customer.class);
        Customer customer = responseEntity.getBody();
        log.info("actual is" + customer);
    }

    @Test
    public void testRetrieveStudentCourse() {

        headers.add("Accept", "application/json");
        //HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<Customer[]> responseEntity = restTemplate.getForEntity(createURLWithPort("/AnithaEdith/CustomerAPI/1.0.0/customer"),
                Customer[].class);
        List<Customer> customers = Arrays.asList(responseEntity.getBody());
        log.info("actual is" + customers);
    }

    @Test
    public void addCustomerTest() {
        headers.add("Accept", "application/json");

        Customer customer = new Customer(1, "Anitha", 912, "anc@gmail.com", "addr1", "state1", "c", 12354);
        HttpEntity<Customer> entity = new HttpEntity<>(customer, headers);
        ResponseEntity<Customer> response = restTemplate.exchange(
                createURLWithPort("/AnithaEdith/CustomerAPI/1.0.0/customer"),
                HttpMethod.POST, entity, Customer.class);

        Customer responseBody = response.getBody();
        String actual = responseBody.getName();

        String expected = "Anitha";
        assertNotNull(response.getBody().toString());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateCustomerTest() {
        headers.add("Accept", "application/json");

        Customer customer = new Customer(1, "Anitha1", 912, "anc@gmail.com", "addr1", "state1", "c", 12354);
        HttpEntity<Customer> entity = new HttpEntity<>(customer, headers);
        ResponseEntity<Customer> response = restTemplate.exchange(
                createURLWithPort("/AnithaEdith/CustomerAPI/1.0.0/customer"),
                HttpMethod.PUT, entity, Customer.class);

        Customer responseBody = response.getBody();
        String actual = responseBody.getName();

        String expected = "Anitha1";
        assertNotNull(response.getBody().toString());
        Assert.assertEquals(expected, actual);

        log.info(response.getHeaders().toString());
    }

    @Test
    public void deleteCustomerTest() {
        headers.add("Accept", "application/json");

        Customer customer = new Customer(1, "Anitha1", 912, "anc@gmail.com", "addr1", "state1", "c", 12354);
        HttpEntity<Customer> entity = new HttpEntity<>(customer, headers);
        ResponseEntity<Void> response = restTemplate.exchange(
                createURLWithPort("/AnithaEdith/CustomerAPI/1.0.0/customer"),
                HttpMethod.DELETE, entity, Void.class);

    }

    @Test
    public void deleteCustomerByIdTest() {
        headers.add("Accept", "application/json");

        Map<String, Integer> params = new HashMap<>();
        params.put("customerId", 1);

        String urlWithPort = createURLWithPort("/AnithaEdith/CustomerAPI/1.0.0/customer/{customerId}");
        restTemplate.delete(urlWithPort, params);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}


