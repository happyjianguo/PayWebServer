package com.work.general;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class GeneralModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneralModuleApplication.class, args);
	}
}
