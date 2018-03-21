package io.pivotal.examples.db2sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@DirtiesContext
public class SpringDataRestTest {

    @Value("${local.server.port}")
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

/*    @Test
    public void testGuardian() {
        ResponseEntity<Guardian> entity = new TestRestTemplate().getForEntity(
                "http://localhost:" + this.port + "/guardians/1", Guardian.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals("fred", entity.getBody().getFirstName());
        assertEquals("flintstone", entity.getBody().getLastName());
        assertEquals("fred@flintstone.net", entity.getBody().getEmail());
        assertEquals(new Integer(12345), entity.getBody().getZipCode());
        Assert.assertNotNull(entity.getBody().getCreateDate());
        Assert.assertNotNull(entity.getBody().getLastModifiedDate());
    }*/

/*    @Test
    public void testChild() {
        ResponseEntity<Child> entity = new TestRestTemplate().getForEntity(
                "http://localhost:" + this.port + "/children/1", Child.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals("pebbles", entity.getBody().getName());
        assertEquals(false, entity.getBody().getSpecialNeeds());
        assertEquals(LocalDate.of(2015, 1, 1), entity.getBody().getBirthDate());
        Assert.assertNotNull(entity.getBody().getCreateDate());
        Assert.assertNotNull(entity.getBody().getLastModifiedDate());
    }*/

/*    @Test
    public void testChild2() {
        ResponseEntity<Child> entity = new TestRestTemplate().getForEntity(
                "http://localhost:" + this.port + "/children/2", Child.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals("bam", entity.getBody().getName());
        assertEquals(true, entity.getBody().getSpecialNeeds());
        assertEquals(LocalDate.of(2015, 11, 11), entity.getBody().getBirthDate());
        Assert.assertNotNull(entity.getBody().getCreateDate());
        Assert.assertNotNull(entity.getBody().getLastModifiedDate());
    }*/
}
