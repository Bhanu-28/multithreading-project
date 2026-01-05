package com.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
		name = "Customer",
		description = "Schema to hold Customer and Account Information"
)
public class CustomerDto {
	
	@Schema(
			description = "Name of the Customer",
			example = "Bhanu Pradeep"
	)
	@NotEmpty(message = "Name can not be a null or empty")
	@Size(min=5,max=30, message="The length of the customer name should be between 5 and 30")
	private String name;
	
	@Schema(
			description = "Email of the Customer",
			example = "test123@gmail.com"
	)
	@NotEmpty(message = "Email address can not be a null or empty")
	@Email(message = "Email address should be a valid value")
	private String email;
	
	@Schema(
			description = "Mobile Number of the Customer",
			example = "1234567890"
	)
	@Pattern(regexp = "^[0-9]{10}$",message = "Mobile number must be 10 digits")
	private String mobileNumber;
	
	@Schema(
			description = "Account related information for the customer"
	)
	private AccountDto accountDto;

}
