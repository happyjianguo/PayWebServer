package com.work.serviceschedule;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableFeignClients
@MapperScan("com.work.generaldb.mapper")
@ComponentScan(basePackages = {"com.work.*"})
@SpringBootApplication
public class ServiceScheduleApplication {

	public static void main(String[] args) {


		SpringApplication.run(ServiceScheduleApplication.class, args);

	}
}
