package com.bank.accounts.dto;

import lombok.Data;

@Data
public class CustomerInfoDto {
	
	
	private String name;
	
	private String email;
	
	private String mobileNumber;
	
	private Long accountNumber;

	private String accountType;
	
	private String branchAddress;
}
