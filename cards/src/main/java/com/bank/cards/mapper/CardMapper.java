package com.bank.cards.mapper;

import com.bank.cards.dto.CardDto;
import com.bank.cards.entity.Card;

public class CardMapper {
	
	public static CardDto mapToCardDto(Card card, CardDto cardDto) {
		
		cardDto.setAmountUsed(card.getAmountUsed());
		cardDto.setAvailableAmount(card.getAvailableAmount());
		cardDto.setCardNumber(card.getCardNumber());
		cardDto.setCardType(card.getCardType());
		cardDto.setMobileNumber(card.getMobileNumber());
		cardDto.setTotalLimit(card.getTotalLimit());
		
		return cardDto;
	}
	
	
	public static Card mapToCard(CardDto cardDto, Card card) {
		
		card.setMobileNumber(cardDto.getMobileNumber());
		card.setCardNumber(cardDto.getCardNumber());
		card.setCardType(cardDto.getCardType());
		card.setTotalLimit(cardDto.getTotalLimit());
		card.setAmountUsed(cardDto.getAmountUsed());
		card.setAvailableAmount(cardDto.getAvailableAmount());
		
		return card;
	}

}
