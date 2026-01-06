package com.bank.loans.services.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.bank.loans.constants.LoanConstants;
import com.bank.loans.dto.LoanDto;
import com.bank.loans.entity.Loan;
import com.bank.loans.exception.LoanAlreadyExistsException;
import com.bank.loans.exception.ResourceNotFoundException;
import com.bank.loans.maper.LoanMapper;
import com.bank.loans.repository.ILoanRepository;
import com.bank.loans.services.ILoansService;

import lombok.AllArgsConstructor;

 /**
 * The Class LoanServiceImpl.
 */
@Service
@AllArgsConstructor
public class LoanServiceImpl implements ILoansService{
	
	/** The i loan repository. */
	private ILoanRepository iLoanRepository;

	/**
	 * Creates the loan.
	 *
	 * @param mobileNumber the mobile number
	 */
	@Override
	public void createLoan(String mobileNumber) {
		
		Optional<Loan> loanExists = iLoanRepository.findByMobileNumber(mobileNumber);
		
		if(loanExists.isPresent()) {
			throw new LoanAlreadyExistsException(
					String.format(
						"Loan already exists with the provided mobile number %s",
						mobileNumber.toString() ));
		}
		
		
		
		iLoanRepository.save(createNewLoan(mobileNumber));
		
	}
	
	
	private Loan createNewLoan(String mobileNumber) {
		Loan newLoan = new Loan();
		long randomAccountNumber = 100000000000L + new Random().nextInt(90000000);
		newLoan.setLoanNumber(String.valueOf(randomAccountNumber));
		newLoan.setLoanType("Personal");
		newLoan.setTotalLoan(LoanConstants.TOTAL_LOAN);
		newLoan.setAmountPaid(LoanConstants.AMOUNT_PAID);
		newLoan.setOutstandingAmount(LoanConstants.TOTAL_LOAN);
		newLoan.setMobileNumber(mobileNumber);
		
		return newLoan;
	}

	/**
	 * Fetch loan details.
	 *
	 * @param mobileNumber the mobile number
	 * @return the loan dto
	 */
	@Override
	public LoanDto fetchLoanDetails(String mobileNumber) {
		
		Loan loan = iLoanRepository.findByMobileNumber(mobileNumber).orElseThrow(
					() -> new ResourceNotFoundException("loan","mobileNumber",mobileNumber));
		
		
		return LoanMapper.loanToLoanDto(new LoanDto(), loan);
	}

	/**
	 * Update loan details.
	 *
	 * @param loanDto the loan dto
	 * @return true, if successful
	 */
	@Override
	public boolean updateLoanDetails(LoanDto loanDto) {
		boolean isUpdated = false;
		Loan loan = iLoanRepository.findByLoanNumber(loanDto.getLoanNumber()).orElseThrow(
				() -> new ResourceNotFoundException("loan","Loan Number",loanDto.getLoanNumber()
						));
		
		LoanMapper.loanDtoToLoan(loanDto, loan);
		iLoanRepository.save(loan);
		isUpdated = true;
		return isUpdated;
	}

	/**
	 * Delete loan details.
	 *
	 * @param mobileNumber the mobile number
	 * @return true, if successful
	 */
	@Override
	public boolean DeleteLoanDetails(String mobileNumber) {
		boolean isDeleted = false;
		Loan loan = iLoanRepository.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new ResourceNotFoundException("loan","mobileNumber",mobileNumber)
				);
		
		iLoanRepository.deleteById(loan.getLoanId());
		isDeleted = true;
		return isDeleted ;
	}

}
