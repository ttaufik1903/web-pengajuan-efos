package com.rabbai.serviceprospek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceProspekApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProspekApplication.class, args);
	}

}
