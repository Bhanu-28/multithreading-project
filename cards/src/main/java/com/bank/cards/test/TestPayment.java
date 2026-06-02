package com.bank.cards.test;

public class TestPayment {
	
	
	interface PaymentGateWay {
		boolean processPayment(double amount);
		
		// default logic can be over-ridden.
		default void isValid() {
			System.out.println("Validating transaction");
			logAction("Please wait");
		}
		
		// static logic cant be overidden.
		static void recommendUser() {
				System.out.println("By Pass Gateway checks");
			}
		
		
		 // Private method (since Java 9) - shared logic for default/static methods
	    private void logAction(String action) {
	        System.out.println("Log: " + action);
	    }
	}
	
	static class RazorPayGateWay implements PaymentGateWay{
		
		public RazorPayGateWay() {
			
			
		}

		@Override
		public boolean processPayment(double amount) {
			
			System.out.println("Implementing payments Using RazorPay Business Logic");
			return true;
		}
		
		@Override
		public void isValid() {
			System.out.println("RazporPay Validation...");
		}
		
	
	}
		
		
	static class StripePayGateWay implements PaymentGateWay{
		
		public StripePayGateWay() {
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public boolean processPayment(double amount) {
			
			System.out.println("Implementing payments Using Stripe Business Logic");
			return true;
		}
	}
	
	
	//factory method.
	
	public static PaymentGateWay getPaymentImplementation(String type) {
		if(type=="razorPay") {
			return new RazorPayGateWay();
		}
		else if(type=="Stripe"){
			return new StripePayGateWay();
		}
		throw new IllegalArgumentException("Invalid payment gateway");
	}
	
	
	public static void main(String[] args) {
		String paymentType = "Stripe";
		
		PaymentGateWay paymentGateWay = getPaymentImplementation(paymentType);
		paymentGateWay.isValid();
		PaymentGateWay.recommendUser();
		paymentGateWay.processPayment(2000);
		
		
		String payment2Type = "razorPay";
		
		
		PaymentGateWay payment2GateWay = getPaymentImplementation(payment2Type);
		
		payment2GateWay.isValid();
		
		payment2GateWay.processPayment(2000);
		
		
	}

		
	
	
	
		
		
	
		
	}