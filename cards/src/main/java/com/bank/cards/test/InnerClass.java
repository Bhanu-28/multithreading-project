package com.bank.cards.test;

public class InnerClass {
	
	
	public static void main(String[] args) {
		
		Outer.Nested obj = new Outer.Nested();
		
		obj.doSomething();
		
		InnerClass innerClass = new InnerClass();
		Outer1 outer1 = innerClass.new Outer1();
		Outer1.Inner inner = outer1.new Inner();
		
		inner.doSomething();
		
		Outer2 outer2 = new Outer2();
		
		outer2.test();
		
		
		// Anonymous Inner class.
		// No class name → quick implementation
		Animal animal = new Animal() {
			
			@Override
			public void Sound() {

				System.out.println("Bark");
			}
		};	
		
		animal.Sound();
	}
	
	interface Animal{
		void Sound();
	}
	
	// static Nested Class
	 class Outer{
		static class Nested{
			void doSomething() {
				System.out.println("Hello All I am Static Nested class");
			}
		}
	}
	 
	 
//	 Non static Inner Class
	 
	  class Outer1{
		 class Inner{
			 void doSomething() {
					System.out.println("Hello I am static Inner class");
				} 
		 }
	 }
	  
	  
	 static class Outer2{
		 void test() {
			 class LocalMethodInner {
				 void show() {
					 System.out.println("Local Inner class");
				 }
			 }
			 
			 LocalMethodInner localMethodInner = new LocalMethodInner();
			 localMethodInner.show();
		 }
	 }
	  
	  
	  

}
