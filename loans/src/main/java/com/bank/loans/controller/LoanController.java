package com.bank.loans.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.loans.constants.LoanConstants;
import com.bank.loans.dto.LoanDto;
import com.bank.loans.dto.ResponseDto;
import com.bank.loans.services.ILoansService;

import lombok.AllArgsConstructor;


@Controller
@RestController
@RequestMapping(path = "/api/",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class LoanController {

	private ILoansService iLoansService;
	
	@GetMapping("/createLoan")
	public ResponseEntity<ResponseDto> createLoan(@RequestParam String mobileNumber) {
		
		iLoansService.createLoan(mobileNumber);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body( new ResponseDto(LoanConstants.STATUS_201,LoanConstants.MESSAGE_201));
		
	}
	
	@GetMapping("/fetchLoan")
	public ResponseEntity<LoanDto> fetchLoanDetails(@RequestParam String mobileNumber){
		
		LoanDto loanDto =  iLoansService.fetchLoanDetails(mobileNumber);
		
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(loanDto);
	}
	
	@PutMapping("/updateLoan")
	public ResponseEntity<ResponseDto>  updateLoanDetails(@RequestBody LoanDto loanDto){
		
		boolean isUpdated = iLoansService.updateLoanDetails(loanDto);
		
		if(isUpdated) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
		}else {
			return ResponseEntity
					.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417));
		}
		
	}
	
	
	@DeleteMapping("/deleteloan")
	public ResponseEntity<ResponseDto> deleteLoan(@RequestParam String mobileNumber){
		
		boolean isDeleted = iLoansService.DeleteLoanDetails(mobileNumber);
		
		if(isDeleted) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
		}else {
			return ResponseEntity
					.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_DELETE));
		}
	}
	 
}
