package com.bank.accounts.services;

import com.bank.accounts.dto.AccountDto;
import com.bank.accounts.dto.CustomerDto;

public interface IAccountsService {
	
	
	void createAccount(CustomerDto customerDto);	
	
	CustomerDto fetchCustomerDetails(String mobileNumber);
	
	AccountDto fetchAccountDetails(Long accountNumber);
	
	CustomerDto fetchCustomerInfoDetails(String mobileNumber);
	
	boolean updateAccountDetails(CustomerDto customerDto);
	
	
	boolean deleteAccount(String mobileNumber);


}
