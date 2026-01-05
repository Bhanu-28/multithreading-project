package com.bank.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(name = "Loans",
description = "Schema to hold Loan information"
)
@Data
public class LoanDto {
	
	@NotEmpty(message = "Mobile Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digits")
	@Schema(
			description = "mobile Number Linked to loan account",
			example = "1234567890" 
//			^[0-9]{10}$
		)
	private String mobileNumber;
	
	
	@NotEmpty(message = "Loan Number can not be a null or empty")
    @Pattern(regexp="(^$|[0-9]{12})",message = "Loan Number must be 12 digits")
	@Schema(
			description = "mobile Number Linked to loan account",
			example = "108069789467"
		)
	private String loanNumber;
	
	@NotEmpty(message = "LoanType can not be a null or empty")
    @Schema(
            description = "Type of the loan", example = "Home Loan"
    )
	private String loanType;
	
	
	@Positive(message = "Total loan amount should be greater than zero")
    @Schema(
            description = "Total loan amount", example = "100000"
    )
	private int totalLoan;
	
	
    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    @Schema(
            description = "Total loan amount Paid", example = "100"
    )
	private int amountPaid;
	
    
    @PositiveOrZero(message = "Total out standing amount paid should be equal or greater than zero")
    @Schema(
            description = "Total outstanding amount against a loan", example = "9100"
    )
	private int outStandingAmount;

}
