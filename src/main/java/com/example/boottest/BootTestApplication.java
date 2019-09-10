package com.example.boottest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BootTestApplication {
	private static final Logger logger = LoggerFactory.getLogger(BootTestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BootTestApplication.class, args);
	}

}
