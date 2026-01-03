package com.bank.accounts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.bank.accounts.entity.Account;

import jakarta.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{
	
	Optional<Account> findByCustomerId(Long customerId);
	
	Optional<Account> findByAccountNumber(Long accountNumber);
	
	@Transactional
	@Modifying
	void deleteByCustomerId(Long customerId);
	
}
