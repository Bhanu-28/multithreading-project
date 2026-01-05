package com.bank.loans.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ErrorResponseDto {
	
	public  String apiPath;
	
	public  HttpStatus errorCode;
	
	public  String errorMessage;
	
	public LocalDateTime errorTime; 
	
	
}
