
package com.bank.accounts.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.bank.accounts.constants.AccountsConstants;
import com.bank.accounts.dto.CustomersDto;
import com.bank.accounts.entity.Accounts;
import com.bank.accounts.entity.Customer;
import com.bank.accounts.exception.CustomerAlreadyExistsException;
import com.bank.accounts.mapper.CustomersMapper;
import com.bank.accounts.repository.AccountRepository;
import com.bank.accounts.repository.CustomerRepository;
import com.bank.accounts.services.IAccountsService;

import lombok.AllArgsConstructor;

@Service @AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService{
	
	private AccountRepository accountRepository;
	
	private CustomerRepository customerRepository;

	@Override
	public void createAccount(CustomersDto customerDto) {
		
		Customer customer =  CustomersMapper.mapToCustomer(new Customer(), customerDto);
		
		Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
		
		if(optionalCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException("Customer already registered with given Mobile Number "+customer.getMobileNumber());
		}
		
		customer.setCreatedAt(LocalDateTime.now());
		customer.setCreatedBy("anonymous");
		Customer savedCustomer = customerRepository.save(customer);
		

		accountRepository.save(createNewAccount(savedCustomer));
	}
	
	private Accounts createNewAccount(Customer customer) {
		
		Accounts newAccount = new Accounts();
		
		newAccount.setCustomerId(customer.getCustomerId());
		
		long randomAccountNumber = 1000000000L + new Random().nextInt(900000000);
		
		newAccount.setAccountNumber(randomAccountNumber);
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		newAccount.setBranchAddress(AccountsConstants.BRANCH_ADDRESS);
		
		newAccount.setCreatedAt(LocalDateTime.now());
		newAccount.setCreatedBy("anonymous");
		
		return newAccount;
		
	}

}
