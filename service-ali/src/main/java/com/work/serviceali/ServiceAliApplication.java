package com.work.serviceali;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@EnableEurekaClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.work."})
public class ServiceAliApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceAliApplication.class, args);
	}
}
