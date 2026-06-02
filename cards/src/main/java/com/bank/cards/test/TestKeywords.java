package com.bank.cards.test;

public class TestKeywords {
	
	
	// static variables shared across object.
	// static method no object needed can use class Name directly.
	//	static block Runs once when class loads
	
	// Static is class-level. It’s useful for shared data and utility methods, but overusing it hurts testability.
	// limitation.
	// Static method cannot access instance variables
	//
	
	int x = 10;
	
//	Static method cannot access instance variables
	static void show() {
//		System.out.println(x);  // error
	}
	
	
	public static void main(String[] args) {
        new Config();
    }
	
	static class Config {
		
		// Runs once when class loads
	    static {
	        System.out.println("Static block executed");
	    }
	}

	    
}



