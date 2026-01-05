package com.bank.loans.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class Loan extends BaseEnity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long loanId;
	
	private String mobileNumber;
	
	private String loanNumber;
	
	private String loanType;
	
	private Long totalLoan;
	
	private Long amountPaid;
	
//	@Column(name = "outstanding_amount")
	private Long outstandingAmount;
	

}
