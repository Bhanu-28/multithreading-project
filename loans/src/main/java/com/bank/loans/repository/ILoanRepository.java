package com.bank.loans.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.loans.entity.Loan;

@Repository
public interface ILoanRepository extends JpaRepository<Loan, Long>{
	
	Optional<Loan> findByMobileNumber(String mobileNumber);

}
