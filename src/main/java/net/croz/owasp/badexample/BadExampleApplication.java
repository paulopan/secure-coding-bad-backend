package net.croz.owasp.badexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BadExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BadExampleApplication.class, args);
	}

}
