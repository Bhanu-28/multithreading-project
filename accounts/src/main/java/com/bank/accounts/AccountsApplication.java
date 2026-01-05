package com.bank.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "YOLO BANK Accounts microservice REST API Documentation",
				description = "Here we Create,Fetch,update and Delete Account Details from YOLO BANK",
				version = "v1",
				contact = @Contact(
							name = "Bhanu Pradeep",
							email = "gorrebhanupradeepkumar@gmail.com",
							url = "https://www.linkedin.com/in/bhanu-pradeep/"
						)
				)
		)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}


/*
 * for some reason if u want to keep the packages outside of this package 
 * U need to mention here to the main class.
 * 
 * @ComponentScans({ @ComponentScan("com.package.accounts.controller") })
 * 
 * @EnableJpaRepositories("com.package.accounts.repository")
 * @EntityScan("com.package.accounts.model")

*/