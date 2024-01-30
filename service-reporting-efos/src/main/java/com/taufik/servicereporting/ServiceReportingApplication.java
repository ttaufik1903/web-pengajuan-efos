package com.taufik.servicereporting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
public class ServiceReportingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceReportingApplication.class, args);
	}

}
