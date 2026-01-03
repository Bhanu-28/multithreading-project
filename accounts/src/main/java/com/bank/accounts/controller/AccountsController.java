package com.bank.accounts.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.accounts.constants.AccountsConstants;
import com.bank.accounts.dto.AccountDto;
import com.bank.accounts.dto.CustomerDto;
import com.bank.accounts.dto.ResponseDto;
import com.bank.accounts.services.IAccountsService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/account/",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class AccountsController {
	
	private IAccountsService iAccountsService;
	
	@PostMapping("/create")
	public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto){
		
		iAccountsService.createAccount(customerDto);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201,AccountsConstants.MESSAGE_201));
	}
	
	@GetMapping("/fetchAccountDetails")
	public AccountDto fetchAccountDetails(@RequestParam Long accountNumber) {
			
		return	iAccountsService.fetchAccountDetails(accountNumber);
		
	}
	
	@GetMapping("/fetchCustomerDetails")
	public CustomerDto fetchCustomerDetails(@RequestParam String mobileNumber) {
			
		return	iAccountsService.fetchCustomerDetails(mobileNumber);
		
	}
	
	@GetMapping("/fetchCustomerInfoDetails")
	public ResponseEntity<CustomerDto> fetchCustomerInfoDetails(@RequestParam String mobileNumber) {
			
			CustomerDto customerDto = iAccountsService.fetchCustomerInfoDetails(mobileNumber);
			
			return ResponseEntity.status(HttpStatus.OK).body(customerDto);
		
	}
	
	@PutMapping("/updateAccountDetails")
	public ResponseEntity<ResponseDto> updateAccountDetails(@RequestBody CustomerDto customerDto) {
		
		 boolean isUpdated = iAccountsService.updateAccountDetails(customerDto);
		 
		 if(isUpdated) {
			 return ResponseEntity
					 .status(HttpStatus.OK)
					 .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		 }else {
			 return ResponseEntity
					 .status(HttpStatus.EXPECTATION_FAILED)
					 .body(new ResponseDto(AccountsConstants.MESSAGE_500, AccountsConstants.MESSAGE_417));
		 }
		
	}
	
	@DeleteMapping("/deleteAccount")
	public ResponseEntity<ResponseDto> deleteAccount(@RequestParam String mobileNumber){
		
		boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
		
		if(isDeleted) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		}else {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
		}
		
	}
	
	
	
	


}
