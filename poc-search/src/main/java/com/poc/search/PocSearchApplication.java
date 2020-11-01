package com.poc.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.poc.search")
@EnableJpaRepositories("com.poc.search")
@EntityScan("com.poc.search")
public class PocSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(PocSearchApplication.class, args);
	}

	

}
