package com.bank.loans.maper;

import com.bank.loans.dto.LoanDto;
import com.bank.loans.entity.Loan;

public class LoanMapper {
	
	private LoanMapper() {
		
	}
	
	public static Loan loanDtoToLoan(LoanDto loanDto, Loan loan) {
		
		loan.setTotalLoan(loanDto.getTotalLoan());
		loan.setOutstandingAmount(loanDto.getOutStandingAmount());
		loan.setMobileNumber(loanDto.getMobileNumber());
		loan.setLoanType(loanDto.getLoanType());
		loan.setLoanNumber(loanDto.getLoanNumber());
		loan.setAmountPaid(loanDto.getAmountPaid());
		
		return loan;
		
	}
	
	
	public static LoanDto loanToLoanDto(LoanDto loanDto,Loan loan) {
		
		
		loanDto.setTotalLoan(loan.getTotalLoan());
		loanDto.setOutStandingAmount(loan.getOutstandingAmount());
		loanDto.setMobileNumber(loan.getMobileNumber());
		loanDto.setLoanType(loan.getLoanType());
		loanDto.setLoanNumber(loan.getLoanNumber());
		loanDto.setAmountPaid(loan.getAmountPaid());
		
		
		return loanDto;
	}

}
