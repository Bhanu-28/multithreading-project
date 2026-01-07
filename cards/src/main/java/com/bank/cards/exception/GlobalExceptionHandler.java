package com.bank.cards.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bank.cards.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		Map<String,String> validationErrors = new HashMap<>();
		
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		
		allErrors.forEach((error)-> 
		
						{
							String fieldName = ((FieldError)error).getField();
							String fieldValue = error.getDefaultMessage();
							validationErrors.put(fieldName, fieldValue);
						}
				
				);
		return new ResponseEntity<>(validationErrors,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleGlobalException(WebRequest webRequest, Exception exception){
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
											webRequest.getDescription(false),
											HttpStatus.INTERNAL_SERVER_ERROR,
											exception.getMessage(),
											LocalDateTime.now()	
											);
		
		return new ResponseEntity<>(errorResponseDto,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(CardAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleCardAlreadyExistsException(WebRequest webRequest, Exception exception){
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
											webRequest.getDescription(false),
											HttpStatus.EXPECTATION_FAILED,
											exception.getMessage(),
											LocalDateTime.now()	
											);
		
		return new ResponseEntity<>(errorResponseDto,HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(WebRequest webRequest, Exception exception){
		
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
											webRequest.getDescription(false),
											HttpStatus.NOT_FOUND,
											exception.getMessage(),
											LocalDateTime.now()	
											);
		
		return new ResponseEntity<>(errorResponseDto,HttpStatus.NOT_FOUND);
	}
	
	
	
}
