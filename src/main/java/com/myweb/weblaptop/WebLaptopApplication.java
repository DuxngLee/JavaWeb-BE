package com.myweb.weblaptop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
		//(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
public class WebLaptopApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebLaptopApplication.class, args);
	}

}
