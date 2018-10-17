package io.pivotal.examples.db2sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class SpringDataRestTest {

    @LocalServerPort
    private int port;

    @Test
    public void testEmployeeById() {
        ResponseEntity<Employee> entity = new TestRestTemplate().getForEntity(
                "http://localhost:" + this.port + "/employees/20", Employee.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals(LocalDate.of(1978, 2, 2), entity.getBody().getBirthDate());
        assertEquals("THOMPSON", entity.getBody().getLastName());
        assertEquals("MANAGER ", entity.getBody().getPosition());
        assertEquals(new Double(94250), entity.getBody().getSalary());
    }

}
