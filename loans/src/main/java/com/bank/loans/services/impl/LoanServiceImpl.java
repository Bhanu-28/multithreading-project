package com.bank.loans.services.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.loans.constants.LoanConstants;
import com.bank.loans.dto.LoanDto;
import com.bank.loans.entity.Loan;
import com.bank.loans.exception.LoanAlreadyExistsException;
import com.bank.loans.exception.LoanNotFoundException;
import com.bank.loans.maper.LoanMapper;
import com.bank.loans.repository.ILoanRepository;
import com.bank.loans.services.ILoansService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoansService{
	
	private ILoanRepository iLoanRepository;

	@Override
	public void createLoan(String mobileNumber) {
		
		Optional<Loan> loanExists = iLoanRepository.findByMobileNumber(mobileNumber);
		
		if(loanExists.isPresent()) {
			throw new LoanAlreadyExistsException(
					String.format(
						"Loan already exists with the provided mobile number %s",
						mobileNumber.toString() ));
		}
		
		Loan loan = new Loan();
		long randomAccountNumber = 100000000000L + new Random().nextInt(90000000);
		loan.setLoanNumber(String.valueOf(randomAccountNumber));
		loan.setLoanType("Personal");
		loan.setTotalLoan(LoanConstants.TOTAL_LOAN);
		loan.setAmountPaid(LoanConstants.AMOUNT_PAID);
		loan.setOutstandingAmount(LoanConstants.TOTAL_LOAN);
		loan.setMobileNumber(mobileNumber);
		
		iLoanRepository.save(loan);
		
	}

	@Override
	public LoanDto fetchLoanDetails(String mobileNumber) {
		
		Loan loan = iLoanRepository.findByMobileNumber(mobileNumber).orElseThrow(
					() -> new LoanNotFoundException(String.format(
						"Could not fetch loan details with the given mobile number %s",
						mobileNumber))
					);
		
		
		return LoanMapper.loanToLoanDto(new LoanDto(), loan);
	}

	@Override
	public boolean updateLoanDetails(LoanDto loanDto) {
		boolean isUpdated = false;
		Loan loan = iLoanRepository.findByMobileNumber(loanDto.getMobileNumber()).orElseThrow(
				() -> new LoanNotFoundException(String.format(
					"Could not fetch loan details with the given mobile number %s",
					loanDto.getMobileNumber()))
				);
		
		LoanMapper.loanDtoToLoan(loanDto, loan);
		iLoanRepository.save(loan);
		isUpdated = true;
		return isUpdated;
	}

	@Override
	public boolean DeleteLoanDetails(String mobileNumber) {
		boolean isDeleted = false;
		Loan loan = iLoanRepository.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new LoanNotFoundException(String.format(
					"Could not fetch loan details with the given mobile number %s",
					mobileNumber))
				);
		
		iLoanRepository.deleteById(loan.getLoanId());
		isDeleted = true;
		return isDeleted ;
	}

}
