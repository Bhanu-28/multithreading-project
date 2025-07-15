package com.bank.accounts.mapper;

import com.bank.accounts.dto.CustomersDto;
import com.bank.accounts.entity.Customer;

public class CustomersMapper {

	public static CustomersDto mapToCustomersDto(Customer customer,CustomersDto customersDto) {
		
		customersDto.setEmail(customer.getEmail());
		customersDto.setMobileNumber(customer.getMobileNumber());
		customersDto.setName(customer.getName());
		
		return customersDto;
	}
	
	
	public static Customer mapToCustomer(Customer customer, CustomersDto customersDto) {
		customer.setEmail(customersDto.getEmail());
		customer.setMobileNumber(customersDto.getMobileNumber());
		customer.setName(customersDto.getName());
		
		return customer;
	}
}
