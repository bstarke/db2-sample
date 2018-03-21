package io.pivotal.examples.db2sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Db2SampleApplication {

	public static void main(String[] args) {
		log.debug("Starting Application");
		SpringApplication.run(Db2SampleApplication.class, args);
	}
}
