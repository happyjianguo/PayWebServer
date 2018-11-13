package com.work.payweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@MapperScan("com.work.generaldb.mapper")
@SpringBootApplication
public class PayWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayWebApplication.class, args);
	}
}
