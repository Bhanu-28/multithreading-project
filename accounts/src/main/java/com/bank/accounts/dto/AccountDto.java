package com.bank.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountDto {
	
	@NotEmpty(message = "Account number can not be null or empty")
	@Pattern(regexp = "^[0-9]{10}$",message = "Account number must be 10 digits")
	private Long accountNumber;
	
	@NotEmpty(message = "AccountType can not be null or empty")
	private String accountType;
	
	@NotEmpty(message = "Branch Address can not be null or empty")
	private String branchAddress;

}
