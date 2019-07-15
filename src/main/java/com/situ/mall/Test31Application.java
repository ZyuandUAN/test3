package com.situ.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
 

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages= {"com.situ.crm.*.controller,com.situ.crm.*.service,com.situ.email"})
@MapperScan(basePackages="com.situ.crm.*.mapper")
public class Test31Application {
	public static void main(String[] args) {
		SpringApplication.run(Test31Application.class, args);
	}
}
//,com.situ.base.controller,com.situ.base.service
//,com.situ.base.mapper