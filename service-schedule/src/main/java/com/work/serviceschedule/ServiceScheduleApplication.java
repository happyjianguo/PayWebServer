package com.work.serviceschedule;

import org.apache.ibatis.session.Configuration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableEurekaClient
@MapperScan("com.work.generaldb.mapper")
@ComponentScan(basePackages = {"com.work.*"})
@SpringBootApplication
public class ServiceScheduleApplication {

	public static void main(String[] args) {


		SpringApplication.run(ServiceScheduleApplication.class, args);

	}
}
