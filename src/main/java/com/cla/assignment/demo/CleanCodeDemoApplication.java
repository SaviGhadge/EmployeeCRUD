package com.cla.assignment.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CleanCodeDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleanCodeDemoApplication.class, args);
	}

}
