/**
 * 
 */
package com.bank.cards.services.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.bank.cards.constants.CardConstants;
import com.bank.cards.dto.CardDto;
import com.bank.cards.entity.Card;
import com.bank.cards.exception.CardAlreadyExistsException;
import com.bank.cards.exception.ResourceNotFoundException;
import com.bank.cards.mapper.CardMapper;
import com.bank.cards.repository.ICardRepository;
import com.bank.cards.services.ICardService;

import lombok.AllArgsConstructor;

/**
 * 
 */
@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService{
	
	private ICardRepository iCardRepository;

	@Override
	public void createCard(String mobileNumber) {
		
		
		Optional<Card> existingCard = iCardRepository.findByMobileNumber(mobileNumber);
		
		if(existingCard.isPresent()) {
			throw new CardAlreadyExistsException(
					String.format("Card already exists with the Given Mobile Number : %s",mobileNumber));
		}
		
		iCardRepository.save(createNewCard(mobileNumber)) ;
		
		
	}

	private Card createNewCard(String mobileNumber) {
		
		Card card = new Card();
		Long randomNumber = 100000000000L + new Random().nextInt(900000000);
		card.setMobileNumber(mobileNumber);
		card.setCardNumber(randomNumber.toString());
		card.setCardType(CardConstants.PLATINUM_CARD_TYPE);
		card.setTotalLimit(CardConstants.TOTAL_LIMIT);
		card.setAmountUsed(CardConstants.AMOUNT_USED);
		card.setAvailableAmount(CardConstants.AVAILABLE_AMOUNT);
		
		return card;
		
	}

	@Override
	public CardDto fetchCardDetails(String mobileNumber) {
		
		Card cardDetails = iCardRepository.findByMobileNumber(mobileNumber).orElseThrow(
							() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
							);
		
		
		return CardMapper.mapToCardDto(cardDetails,new CardDto());
	}

	@Override
	public boolean updateCardDetails(CardDto cardDto) {
		
		boolean isUpdated = false;
		Card existingCardDetails = iCardRepository.findByCardNumber(cardDto.getCardNumber()).orElseThrow(
					() -> new ResourceNotFoundException("Card", "Card Number", cardDto.getCardNumber())
				);
		
		iCardRepository.save(CardMapper.mapToCard(cardDto, existingCardDetails));
		isUpdated = true;
		return isUpdated;
	}

	@Override
	public boolean deleteCardDetails(String mobileNumber) {
		boolean isDeleted = false;
		Card cardToDelete = iCardRepository.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
			);
		
		iCardRepository.deleteById(cardToDelete.getCardId());
		
		isDeleted = true;
		return isDeleted;
	}

}
