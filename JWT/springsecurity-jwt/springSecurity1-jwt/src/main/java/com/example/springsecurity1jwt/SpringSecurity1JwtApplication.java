package com.example.springsecurity1jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringSecurity1JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity1JwtApplication.class, args);
	}

}
