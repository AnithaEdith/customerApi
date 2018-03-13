package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.Swagger2SpringBoot;
import io.swagger.model.Customer;
import io.swagger.repository.CustomerRepository;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Swagger2SpringBoot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class ITCustomerApiControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @MockBean
    private CustomerRepository customerRepository;
    @Autowired private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    HttpHeaders headers = new HttpHeaders();
    private static final Logger log = LoggerFactory.getLogger(ITCustomerApiControllerTest.class);

    @Before
    public void setup() {
        Customer customer=new Customer(1, "Anitha", 912, "anc@gmail.com", "addr1" ,"state1", "c", 12354);
        Customer updatecustomer=new Customer(1, "Anitha1", 912, "anc1@gmail.com", "addr2" ,"state2", "c", 12354);
        given(this.customerService.
                listcustomerbyid(1)
        ).willReturn(customer);

        given(this.customerService.
                listcustomerbyZipCode(12345)
        ).willReturn(customer);

        given(this.customerService.
                addcustomer(updatecustomer)
        ).willReturn(updatecustomer);
    }

    @Test
    public void testSearchCustomerByZipCode() throws JSONException {

        headers.add("Accept","application/json");
        String searchcustomerByZipCodeQuery = createURLWithPort("/AnithaEdith/CustomerAPI/1.0.0/customersearch");

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(searchcustomerByZipCodeQuery)
                .queryParam("zipcode",12345);

        ResponseEntity<Customer> responseEntity = restTemplate.getForEntity(builder.toUriString(),
                Customer.class);
        Customer customer = responseEntity.getBody();
        log.info("actual is" + customer   );
    }

    @Test
    public void testSearchCustomerById() throws JSONException {
        headers.add("Accept","application/json");
        String searchcustomerByIdQuery = createURLWithPort("/AnithaEdith/CustomerAPI/1.0.0/customersearch");

        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(searchcustomerByIdQuery)
                .queryParam("id",1);

        ResponseEntity<Customer> responseEntity = restTemplate.getForEntity(builder.toUriString(),
                Customer.class);
        Customer customer = responseEntity.getBody();
        log.info("actual is" + customer   );
    }

    @Test
    public void testRetrieveStudentCourse() throws JSONException {

        headers.add("Accept","application/json");
        //HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<Customer[]> responseEntity = restTemplate.getForEntity(createURLWithPort("/AnithaEdith/CustomerAPI/1.0.0/customer"),
                Customer[].class);
        List<Customer> customers = Arrays.asList(responseEntity.getBody());
        log.info("actual is" + customers   );
    }

    @Test
    public void addCustomerTest() throws JSONException, IOException {
        headers.add("Accept","application/json");

     Customer customer=new Customer(1, "Anitha", 912, "anc@gmail.com", "addr1" ,"state1", "c", 12354);
     HttpEntity<Customer> entity = new HttpEntity<Customer>(customer, headers);
     ResponseEntity<Customer> response = restTemplate.exchange(
              createURLWithPort("/AnithaEdith/CustomerAPI/1.0.0/customer"),
                HttpMethod.POST, entity, Customer.class);

        Customer responseBody = response.getBody();
        String actual=responseBody.getName().toString();

        String expected="Anitha";
        assertNotNull( response.getBody().toString());
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void updateCustomerTest() throws JSONException, IOException {
        headers.add("Accept","application/json");

        Customer customer=new Customer(1, "Anitha1", 912, "anc@gmail.com", "addr1" ,"state1", "c", 12354);
        HttpEntity<Customer> entity = new HttpEntity<Customer>(customer, headers);
        ResponseEntity<Customer> response = restTemplate.exchange(
                createURLWithPort("/AnithaEdith/CustomerAPI/1.0.0/customer"),
                HttpMethod.PUT, entity, Customer.class);

        Customer responseBody = response.getBody();
        String actual=responseBody.getName().toString();

        String expected="Anitha1";
        assertNotNull( response.getBody().toString());
        Assert.assertEquals(expected, actual);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}


