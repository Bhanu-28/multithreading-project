package com.bank.accounts.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String resouceName, String fieldName, String fieldValue) {
		super(String.format("%s Not found with the given input data %s : '%s'",resouceName,fieldName,fieldValue));
	}

}
