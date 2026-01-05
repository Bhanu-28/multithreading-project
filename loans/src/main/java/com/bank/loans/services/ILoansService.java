package com.bank.loans.services;


import com.bank.loans.dto.LoanDto;

public interface ILoansService {
	
	
	 void createLoan(String mobileNumber);
	 
	 LoanDto fetchLoanDetails(String mobileNumber);
	 
	 boolean updateLoanDetails(LoanDto loanDto);
	 
	 boolean DeleteLoanDetails(String mobileNumber);

}
