package com.bank.accounts.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.bank.accounts.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> handCustomerAlreadyExistsException(CustomerAlreadyExistsException exception,WebRequest webRequest){
		
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
				webRequest.getDescription(false), 
				HttpStatus.BAD_REQUEST, 
				exception.getMessage(), 
				LocalDateTime.now()
				);
		
		return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({CustomerDetailsNotFoundException.class , AccountDetailsNotFoundException.class})
	public ResponseEntity<ErrorResponseDto> handCustomerDetailsNotFoundException(CustomerDetailsNotFoundException exception,WebRequest webRequest){
		
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
				webRequest.getDescription(false), 
				HttpStatus.NOT_FOUND, 
				exception.getMessage(), 
				LocalDateTime.now()
				);
		
		return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ResourceNotFoundException.class})
	public ResponseEntity<ErrorResponseDto> handResourceNotFoundException(ResourceNotFoundException exception,WebRequest webRequest){
		
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
				webRequest.getDescription(false), 
				HttpStatus.NOT_FOUND, 
				exception.getMessage(), 
				LocalDateTime.now()
				);
		
		return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
	}
	
	
	
}
