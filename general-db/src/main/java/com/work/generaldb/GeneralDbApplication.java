package com.work.generaldb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.work.generaldb.mapper")
@SpringBootApplication
public class GeneralDbApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeneralDbApplication.class, args);
	}
}
