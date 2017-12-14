package com.haah.bear;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = { "com.bear.db.config", "com.haah.bear"})
@MapperScan("com.haah.bear.mapper.**")
public class Application 
{
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
