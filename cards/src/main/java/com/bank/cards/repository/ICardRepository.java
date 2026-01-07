package com.bank.cards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.cards.entity.Card;
import java.util.Optional;


@Repository
public interface ICardRepository extends JpaRepository<Card, Long>{
	
	
	Optional<Card>  findByMobileNumber(String mobileNumber);
	
	Optional<Card> findByCardNumber(String cardNumber);
}
