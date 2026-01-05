package com.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
		name = "Account",
		description = "Schema to hold Account Information"
)
public class AccountDto {
	@Schema(
			description = "AccountNumber of the YoloBank Account",
			example = "1050678925"
	)
	@NotEmpty(message = "Account number can not be null or empty")
	@Pattern(regexp = "^[0-9]{10}$",message = "Account number must be 10 digits")
	private Long accountNumber;
	
	@Schema(
			description = "AccountType of the YoloBank Account",
			example = "Savings"
	)
	@NotEmpty(message = "AccountType can not be null or empty")
	private String accountType;
	
	
	@Schema(
			description = "YoloBank Branch adress",
			example = "123 New york"
	)
	@NotEmpty(message = "Branch Address can not be null or empty")
	private String branchAddress;

}
