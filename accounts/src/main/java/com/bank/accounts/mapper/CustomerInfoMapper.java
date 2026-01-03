package com.bank.accounts.mapper;

import com.bank.accounts.dto.CustomerInfoDto;
import com.bank.accounts.entity.Account;
import com.bank.accounts.entity.Customer;

public class CustomerInfoMapper {
	
	
	public static CustomerInfoDto maptoCustomerInfoDto(Customer customer, Account account) {
		
		CustomerInfoDto customerInfoDto = new CustomerInfoDto();
		customerInfoDto.setName(customer.getName());
		customerInfoDto.setMobileNumber(customer.getMobileNumber());
		customerInfoDto.setEmail(customer.getEmail());
		customerInfoDto.setBranchAddress(account.getBranchAddress());
		customerInfoDto.setAccountType(account.getAccountType());
		customerInfoDto.setAccountNumber(account.getAccountNumber());
		
		return customerInfoDto;
	}

}
