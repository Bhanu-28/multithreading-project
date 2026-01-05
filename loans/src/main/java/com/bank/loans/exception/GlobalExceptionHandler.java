package com.bank.loans.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.bank.loans.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(LoanAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleLoanAlreadyExistsException(Exception exception , WebRequest webRequest){
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
				webRequest.getDescription(false), 
				HttpStatus.BAD_REQUEST, 
				exception.getMessage(),
				LocalDateTime.now());
		
		
		return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(LoanNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleLoanNotFoundException(Exception exception , WebRequest webRequest){
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
				webRequest.getDescription(false), 
				HttpStatus.NOT_FOUND, 
				exception.getMessage(),
				LocalDateTime.now());
		
		
		return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
		
	}

}
