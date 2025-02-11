package com.microservices.cards.service.impl;

import com.microservices.cards.constants.CardConstants;
import com.microservices.cards.dto.CardsDto;
import com.microservices.cards.entity.CardEntity;
import com.microservices.cards.exception.CardAlreadyExistException;
import com.microservices.cards.repository.CardsRepository;
import com.microservices.cards.service.ICardsService;

import java.util.Optional;
import java.util.Random;


public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNo) {
        Optional<CardEntity> cardEntity = cardsRepository.findByMobileNumber(mobileNo);
        if (cardEntity.isPresent()) {
            throw new CardAlreadyExistException("Card already exists with this mobile number " + mobileNo);
        }

        CardEntity card = new CardEntity();
        long randomCardNumber = 1000000000L + new Random().nextInt(900000000);
        card.setCardNumber(String.valueOf(randomCardNumber));
        card.setMobileNumber(mobileNo);
        card.setCardType(CardConstants.SAVINGS);
        card.setTotalLimit(CardConstants.LIMIT);
        card.setAmountUsed(CardConstants.USED);
        card.setAmountAvailable(CardConstants.AVAILABLE);

        cardsRepository.save(card);

    }

}
