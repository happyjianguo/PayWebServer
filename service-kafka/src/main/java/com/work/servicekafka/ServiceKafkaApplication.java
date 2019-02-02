package com.work.servicekafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceKafkaApplication.class, args);
	}

}

