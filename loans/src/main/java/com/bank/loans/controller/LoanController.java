package com.bank.loans.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.loans.constants.LoanConstants;
import com.bank.loans.dto.ErrorResponseDto;
import com.bank.loans.dto.LoanDto;
import com.bank.loans.dto.ResponseDto;
import com.bank.loans.services.ILoansService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@Tag(name = "CRUD REST API's for loans in Yolo Bank",
	 description = "CRUD REST APIs in YoloBank to CREATE, UPDATE, FETCH AND DELETE loan details"
	)
@Controller
@RestController
@Validated
@RequestMapping(path = "/api/",produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class LoanController {

	private ILoansService iLoansService;
	
	@Operation(summary = "create Loan Rest API",
			   description = "Rest API to create loan inside YoloBank"	
			)
	@ApiResponses({
		@ApiResponse(responseCode = "201",
				description = "Http Status Created Successfully"),
		@ApiResponse(responseCode = "500",
		description = "Internal Server error",
		content = @Content(
				schema = @Schema(implementation = ErrorResponseDto.class)
				))
	})
	@GetMapping("/createLoan")
	public ResponseEntity<ResponseDto> createLoan(@RequestParam 
			@Pattern(message = "Mobile Number Must be 10 digits", regexp = "^$|[0-9]{10}") String mobileNumber) {
		
		iLoansService.createLoan(mobileNumber);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body( new ResponseDto(LoanConstants.STATUS_201,LoanConstants.MESSAGE_201));
		
	}
	
	
	@Operation(summary = "Fetch Loan Rest API",
			   description = "Rest API to Fetch loan inside YoloBank"	
			)
	@ApiResponses({
		@ApiResponse(responseCode = "200",
				description = "Http Status OK"),
		@ApiResponse(responseCode = "500",
		description = "Internal Server error",
		content = @Content(
			schema = @Schema(implementation = ErrorResponseDto.class)))
	})
	@GetMapping("/fetchLoan")
	public ResponseEntity<LoanDto> fetchLoanDetails(@RequestParam 
			@Pattern(message = "Mobile Number Must be 10 digits", regexp = "^$|[0-9]{10}") String mobileNumber){
		
		LoanDto loanDto =  iLoansService.fetchLoanDetails(mobileNumber);
		
		
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(loanDto);
	}
	
	@Operation(summary = "Update Loan Rest API",
			   description = "Rest API to Update loan inside YoloBank"	
			)
	@ApiResponses({
		@ApiResponse(responseCode = "200",
				description = "Http Status OK"),
		@ApiResponse(responseCode = "417",
		description = "Expectation Failed"),
		@ApiResponse(responseCode = "500",
					description = "Internal Server error",
					content = @Content(
							schema = @Schema(implementation = ErrorResponseDto.class)
										)
							)
				})
	
	@PutMapping("/updateLoan")
	public ResponseEntity<ResponseDto>  updateLoanDetails(@Valid @RequestBody LoanDto loanDto){
		
		boolean isUpdated = iLoansService.updateLoanDetails(loanDto);
		
		if(isUpdated) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
		}else {
			return ResponseEntity
					.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417));
		}
		
	}
	
	
	@Operation(summary = "Delete Loan Rest API",
			   description = "Rest API to Delete loan inside YoloBank"	
			)
	@ApiResponses({
		@ApiResponse(responseCode = "200",
				description = "Http Status OK"),
		@ApiResponse(responseCode = "417",
		description = "Expectation Failed"),
		@ApiResponse(responseCode = "500",
					description = "Internal Server error",
					content = @Content(
							schema = @Schema(implementation = ErrorResponseDto.class)
										)
							)
				})
	
	@DeleteMapping("/deleteloan")
	public ResponseEntity<ResponseDto> deleteLoan(@RequestParam 
			@Pattern(message = "Mobile Number Must be 10 digits", regexp = "^$|[0-9]{10}") String mobileNumber){
		
		boolean isDeleted = iLoansService.DeleteLoanDetails(mobileNumber);
		
		if(isDeleted) {
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
		}else {
			return ResponseEntity
					.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_DELETE));
		}
	}
	 
}
