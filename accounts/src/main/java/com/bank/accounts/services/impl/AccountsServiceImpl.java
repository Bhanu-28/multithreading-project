
package com.bank.accounts.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.bank.accounts.constants.AccountsConstants;
import com.bank.accounts.dto.AccountDto;
import com.bank.accounts.dto.CustomerDto;
import com.bank.accounts.entity.Account;
import com.bank.accounts.entity.Customer;
import com.bank.accounts.exception.AccountDetailsNotFoundException;
import com.bank.accounts.exception.CustomerAlreadyExistsException;
import com.bank.accounts.exception.ResourceNotFoundException;
import com.bank.accounts.mapper.AccountMapper;
import com.bank.accounts.mapper.CustomerMapper;
import com.bank.accounts.repository.AccountRepository;
import com.bank.accounts.repository.CustomerRepository;
import com.bank.accounts.services.IAccountsService;

import lombok.AllArgsConstructor;

/**
 * The Class AccountsServiceImpl.
 */
@Service @AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService{
	
	/** The account repository. */
	private AccountRepository accountRepository;
	
	/** The customer repository. */
	private CustomerRepository customerRepository;

	/**
	 * Creates the account.
	 *
	 * @param customerDto the customer dto
	 */
	@Override
	public void createAccount(CustomerDto customerDto) {
		
		Customer customer =  CustomerMapper.mapToCustomer(new Customer(), customerDto);
		
		Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customer.getMobileNumber());
		
		if(optionalCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException("Customer already registered with given Mobile Number "+customer.getMobileNumber());
		}
		
		
		Customer savedCustomer = customerRepository.save(customer);
		

		accountRepository.save(createNewAccount(savedCustomer));
	}
	
	/**
	 * Creates the new account.
	 *
	 * @param customer the customer
	 * @return the accounts
	 */
	private Account createNewAccount(Customer customer) {
		
		Account newAccount = new Account();
		
		newAccount.setCustomerId(customer.getCustomerId());
		
		long randomAccountNumber = 1000000000L + new Random().nextInt(900000);
		
		newAccount.setAccountNumber(randomAccountNumber);
		newAccount.setAccountType(AccountsConstants.SAVINGS);
		newAccount.setBranchAddress(AccountsConstants.BRANCH_ADDRESS);
		
		
		return newAccount;
		
	}
	
	
	
	@Override
	public AccountDto fetchAccountDetails(Long accountNumber) {
		
		Account account;
		 Optional<Account> accountDetails = accountRepository.findByAccountNumber(accountNumber);
		 if(accountDetails.isPresent()) {
			 account =  accountDetails.get();
		 }
		 else {
			 throw new AccountDetailsNotFoundException("Account Details Not found with the given account number :"+accountNumber);
		 }
		 
		 return AccountMapper.mapToAccountDto(account, new AccountDto());
	}
	
	

	@Override
	public CustomerDto fetchCustomerDetails(String mobileNumber) {
		/*
		 * Customer customer; Optional<Customer> customerByNumber =
		 * customerRepository.findByMobileNumber(mobileNumber);
		 * if(customerByNumber.isPresent()) { customer = customerByNumber.get(); } else
		 * { throw new
		 * CustomerDetailsNotFoundException("Customer Details Not found with the given mobile number :"
		 * +mobileNumber); }
		 */
		 
		 // Best way to write the above code.
		 
		Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
				 () -> new ResourceNotFoundException("customer","mobileNumber",mobileNumber)
		);
		 
		return CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
	}

	@Override
	public CustomerDto fetchCustomerInfoDetails(String mobileNumber) {
		
		Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
				 () -> new ResourceNotFoundException("customer","mobileNumber",mobileNumber)
		);
		
		Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
				 () -> new ResourceNotFoundException("Account","customerId",customer.getCustomerId().toString())
		);
		
		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		
		customerDto.setAccountDto(AccountMapper.mapToAccountDto(account, new AccountDto()));
		
		return customerDto;
		
		
	}
	
	

	
	

	@Override
	public boolean updateAccountDetails(CustomerDto customerDto) {
		
		boolean isUpdated = false;
		
		AccountDto accountDto = customerDto.getAccountDto();
		
		if(accountDto!=null) {
			Account account = accountRepository.findById(accountDto.getAccountNumber()).orElseThrow(
					() -> new ResourceNotFoundException("Account", "AccountNumber", accountDto.getAccountNumber().toString())
			);
			
			AccountMapper.mapToAccounts(account, accountDto);
			
			Account updatedAccount = accountRepository.save(account);
			
			Customer customer = customerRepository.findById(updatedAccount.getCustomerId()).orElseThrow(
					() -> new ResourceNotFoundException("customer", "Customer Id", updatedAccount.getCustomerId().toString())
			);
			
			CustomerMapper.mapToCustomer(customer, customerDto);
			customerRepository.save(customer);
			isUpdated = true;
			
		}
		
		return isUpdated;
		
	}


	@Override
	public boolean deleteAccount(String mobileNumber) {
		Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
				()  -> new ResourceNotFoundException("customer", "mobileNumber", mobileNumber)
		);
		
		accountRepository.deleteByCustomerId(customer.getCustomerId());
		customerRepository.deleteById(customer.getCustomerId());
		return true;
	}
	
	
	

	

}
