package com.microservices.cards.service.impl;

import com.microservices.cards.constants.CardConstants;
import com.microservices.cards.dto.CardsDto;
import com.microservices.cards.entity.CardEntity;
import com.microservices.cards.exception.CardAlreadyExistException;
import com.microservices.cards.exception.ResourceNotFoundException;
import com.microservices.cards.mapper.CardsMapper;
import com.microservices.cards.repository.CardsRepository;
import com.microservices.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    @Autowired
    private CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNo) {
        Optional<CardEntity> cardEntity = cardsRepository.findByMobileNumber(mobileNo);
        if (cardEntity.isPresent()) {
            throw new CardAlreadyExistException("Card already exists with this mobile number " + mobileNo);
        }

        CardEntity card = new CardEntity();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        card.setCardNumber(String.valueOf(randomCardNumber));
        card.setMobileNumber(mobileNo);
        card.setCardType(CardConstants.SAVINGS);
        card.setTotalLimit(CardConstants.LIMIT);
        card.setAmountUsed(CardConstants.USED);
        card.setAmountAvailable(CardConstants.AVAILABLE);


        CardEntity c = cardsRepository.save(card);


    }

    @Override
    public CardsDto fetchCardDetails(String mobileNo) {
        CardEntity cards = cardsRepository.findByMobileNumber(mobileNo).orElseThrow(
                () -> new ResourceNotFoundException("Card", "Mobile Number", mobileNo)
        );
        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        CardEntity cardEntity = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "Card Number", cardsDto.getCardNumber())
        );
        CardsMapper.mapToCardEntity(cardsDto, cardEntity);
        cardsRepository.save(cardEntity);

        return true;
    }

    @Override
    public boolean deleteCard(String mobileNo) {
        CardEntity cards = cardsRepository.findByMobileNumber(mobileNo).orElseThrow(
                () -> new ResourceNotFoundException("Card", "Mobile Number", mobileNo)
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
