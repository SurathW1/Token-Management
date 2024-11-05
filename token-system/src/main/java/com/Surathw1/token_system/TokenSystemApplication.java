package com.Surathw1.token_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class TokenSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenSystemApplication.class, args);
	}
}
