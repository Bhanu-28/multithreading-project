package com.bank.accounts.mapper;

import com.bank.accounts.dto.AccountDto;
import com.bank.accounts.entity.Account;

public class AccountMapper {
	
	public static AccountDto mapToAccountDto(Account account, AccountDto accountDto) {
		
		accountDto.setAccountNumber(account.getAccountNumber());
		accountDto.setAccountType(account.getAccountType());
		accountDto.setBranchAddress(account.getBranchAddress());
		
		return accountDto;
		
	}
	
	public static Account mapToAccounts(Account account,AccountDto accountDto) {
		account.setAccountNumber(accountDto.getAccountNumber());
		account.setAccountType(accountDto.getAccountType());
		account.setBranchAddress(accountDto.getBranchAddress());
		
		return account;
	}

}
