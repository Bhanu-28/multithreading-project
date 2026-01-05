package com.bank.loans.dto;

import lombok.Data;

@Data
public class LoanDto {
	
	
	private String mobileNumber;
	
	private String loanNumber;
	
	private String loanType;
	
	private Long totalLoan;
	
	private Long amountPaid;
	
	private Long outStandingAmount;

}
