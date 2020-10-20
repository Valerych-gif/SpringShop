package com.geekbrains.springshop;

import com.geekbrains.springshop.utils.RabbitProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringshopApplication implements CommandLineRunner {

	private RabbitProvider rabbitProvider;

	@Autowired
	public void setRabbitProvider(RabbitProvider rabbitProvider) {
		this.rabbitProvider = rabbitProvider;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringshopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		rabbitProvider.openConnect();
		rabbitProvider.logMsg();
	}
}
