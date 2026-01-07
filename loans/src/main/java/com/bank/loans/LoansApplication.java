package com.bank.loans;

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
				title = "YOLO BANK Loans microservice REST API Documentation",
				description = "Here we Create,Fetch,update and Delete Loan Details from YOLO BANK",
				version = "v1",
				contact = @Contact(
							name = "Bhanu Pradeep",
							email = "gorrebhanupradeepkumar@gmail.com",
							url = "https://www.linkedin.com/in/bhanu-pradeep/"
						)
				)
		)
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
