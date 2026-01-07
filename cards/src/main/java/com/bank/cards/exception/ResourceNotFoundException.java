package com.bank.cards.exception;

public class ResourceNotFoundException extends RuntimeException{
	
	
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String resource, String fieldName,String fieldValue) {
		
		super(String.format("%s Not found with the given %s : '%s'", resource,fieldName,fieldValue));
	}

}
