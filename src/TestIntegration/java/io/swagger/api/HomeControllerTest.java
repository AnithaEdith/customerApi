/*
package io.swagger.api;

import io.swagger.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

*/
/*
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
*//*


@RunWith(SpringRunner.class)
@WebMvcTest(HelloController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloController homecontroller;

    @Test
    public void testCustomers() throws Exception {

        // prepare data and mock's behaviour
        Customer empStub = new Customer();
        String URL="";
        // execute
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(URL + "{id}", new Long(1))
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        // verify
        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

        Customer resultcustomer = TestUtils.jsonToObject(result.getResponse()
                .getContentAsString(), Customer.class);
        assertNotNull(resultcustomer);
        assertEquals(1l, resultcustomer.getId().longValue());
    }}
*/
