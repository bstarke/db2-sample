package io.pivotal.examples.db2sample;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.hateoas.mvc.TypeConstrainedMappingJackson2HttpMessageConverter;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.hateoas.MediaTypes.HAL_JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext
public class SpringDataRestTest {

    @Value("${local.server.port}")
    private int port;

    private TestRestTemplate testRestTemplate;

    public SpringDataRestTest() {
        testRestTemplate = new TestRestTemplate();
        List<HttpMessageConverter<?>> existingConverters = testRestTemplate.getRestTemplate().getMessageConverters();
        List<HttpMessageConverter<?>> newConverters = new ArrayList<>();
        newConverters.add(getHalMessageConverter());
        newConverters.addAll(existingConverters);
        testRestTemplate.getRestTemplate().setMessageConverters(newConverters);
    }


    @Test
    public void testEmployeeById() {
        ResponseEntity<Employee> entity = testRestTemplate.getForEntity(
                "http://localhost:" + this.port + "/employees/20", Employee.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(LocalDate.of(1978, 2, 2), entity.getBody().getBirthDate());
        assertEquals("THOMPSON", entity.getBody().getLastName());
        assertEquals("MANAGER ", entity.getBody().getPosition());
        assertEquals(new Double(94250), entity.getBody().getSalary());
    }

    /*@Test
    public void testEmployeeFindByLastNameIgnoreCase() {
        ResponseEntity<Resource<Resource<List<Employee>>>> entity = testRestTemplate.exchange(
                "http://localhost:" + this.port + "/employees/search/findByLastNameIgnoreCase?name=alonzo", HttpMethod.GET, null, new ParameterizedTypeReference<Resource<Resource<List<Employee>>>>(){});
        Resource<Resource<List<Employee>>> resourceResource = entity.getBody();
        System.out.println(resourceResource);
        Resource<List<Employee>> resource = resourceResource.getContent();
        System.out.println(resource);
        List<Employee> list = resource.getContent();
        Employee employee = list.get(0);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals("ROY", employee.getFirstName());
        assertEquals("ALONZO", employee.getLastName());
        assertEquals(new Long(200340), employee.getEmployeeNumber());
    }

    @Test
    public void testEmployeeFindByPositionIgnoreCase() {
        ResponseEntity<Employee[]> entity = new TestRestTemplate().getForEntity(
                "http://localhost:" + this.port + "/employees/search/findByPositionIgnoreCase?position=pres", Employee[].class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals("CHRISTINE", entity.getBody()[0].getFirstName());
        assertEquals("HAAS", entity.getBody()[0].getLastName());
        assertEquals(new Long(10), entity.getBody()[0].getEmployeeNumber());
    }

    @Test
    public void testEmployeeFindBySalaryGreaterThan() {
        ResponseEntity<Employee[]> entity = new TestRestTemplate().getForEntity(
                "http://localhost:" + this.port + "/employees/search/findBySalaryGreaterThan?salary=100000", Employee[].class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals("CHRISTINE", entity.getBody()[0].getFirstName());
        assertEquals("HAAS", entity.getBody()[0].getLastName());
        assertEquals(new Long(10), entity.getBody()[0].getEmployeeNumber());
    }*/


    private HttpMessageConverter getHalMessageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Jackson2HalModule());
        MappingJackson2HttpMessageConverter halConverter = new TypeConstrainedMappingJackson2HttpMessageConverter(ResourceSupport.class);
        halConverter.setSupportedMediaTypes(Arrays.asList(HAL_JSON));
        halConverter.setObjectMapper(objectMapper);
        return halConverter;
    }
}
