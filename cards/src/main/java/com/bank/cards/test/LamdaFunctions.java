package com.bank.cards.test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;

public class LamdaFunctions {
	
	
	
	public static void main(String[] args) {
		
		// Functional Interface + Lambda
		List<String> names = Arrays.asList("John", "Jane", "Bob");
		names.stream()
		     .filter(n -> n.startsWith("J"))
		     .sorted()
		     .forEach(System.out::println);
		
		Optional<Integer> result = Optional.ofNullable(getValue());
		
		result.ifPresent(System.out::println);
	}
	
	
	public static int getValue() {
		return 5;
	}

}
