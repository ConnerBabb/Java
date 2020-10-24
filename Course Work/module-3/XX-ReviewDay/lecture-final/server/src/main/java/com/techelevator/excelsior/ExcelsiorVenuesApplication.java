package com.techelevator.excelsior;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.techelevator")
public class ExcelsiorVenuesApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(ExcelsiorVenuesApplication.class, args);

	}

}
