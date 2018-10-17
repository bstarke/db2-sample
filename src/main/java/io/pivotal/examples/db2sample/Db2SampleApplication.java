package io.pivotal.examples.db2sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class Db2SampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(Db2SampleApplication.class, args);
    }
}
