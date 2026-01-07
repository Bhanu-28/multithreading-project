package com.bank.cards.services;

import com.bank.cards.dto.CardDto;

public interface ICardService {
	
	
	void createCard(String mobileNumber);
	
	
	CardDto fetchCardDetails(String mobileNumber);
	
	
	boolean updateCardDetails(CardDto cardDto);
	
	
	boolean deleteCardDetails(String mobileNumber);
	

}
