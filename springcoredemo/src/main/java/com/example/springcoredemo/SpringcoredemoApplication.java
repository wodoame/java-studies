package com.example.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringcoredemoApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringcoredemoApplication.class, args); // the IoC container
        Dev developer = context.getBean(Dev.class);
        developer.build();
	}

}
