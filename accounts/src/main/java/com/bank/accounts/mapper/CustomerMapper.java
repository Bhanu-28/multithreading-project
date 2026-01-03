package com.bank.accounts.mapper;

import com.bank.accounts.dto.CustomerDto;
import com.bank.accounts.entity.Customer;

public class CustomerMapper {

	public static CustomerDto mapToCustomerDto(Customer customer,CustomerDto customerDto) {
		
		customerDto.setEmail(customer.getEmail());
		customerDto.setMobileNumber(customer.getMobileNumber());
		customerDto.setName(customer.getName());
		
		return customerDto;
	}
	
	
	public static Customer mapToCustomer(Customer customer, CustomerDto customerDto) {
		customer.setEmail(customerDto.getEmail());
		customer.setMobileNumber(customerDto.getMobileNumber());
		customer.setName(customerDto.getName());
		
		return customer;
	}
}
