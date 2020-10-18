package com.geekbrains.springshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringshopApplication.class, args);
	}

}
