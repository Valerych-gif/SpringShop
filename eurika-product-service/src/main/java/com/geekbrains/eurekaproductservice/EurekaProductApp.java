package com.geekbrains.eurekaproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.geekbrains.eurekaproductservice")
public class EurekaProductApp {
	public static void main(String[] args) {
		SpringApplication.run(EurekaProductApp.class, args);
	}
}

