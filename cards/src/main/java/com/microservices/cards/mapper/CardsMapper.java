package com.microservices.cards.mapper;

import com.microservices.cards.dto.CardsDto;
import com.microservices.cards.entity.CardEntity;


public class CardsMapper {

    public static CardsDto mapToCardsDto(CardEntity cardEntity, CardsDto cardsDto) {
        cardsDto.setCardNumber(cardEntity.getCardNumber());
        cardsDto.setCardType(cardEntity.getCardType());
        cardsDto.setMobileNumber(cardEntity.getMobileNumber());
        cardsDto.setTotalLimit(cardEntity.getTotalLimit());
        cardsDto.setAvailableAmount(cardEntity.getAmountAvailable());

        return cardsDto;
    }

    public static CardEntity mapToCardEntity(CardsDto cardsDto, CardEntity cardEntity) {
        cardEntity.setCardNumber(cardsDto.getCardNumber());
        cardEntity.setCardType(cardsDto.getCardType());
        cardEntity.setMobileNumber(cardsDto.getMobileNumber());
        cardEntity.setTotalLimit(cardsDto.getTotalLimit());
        cardEntity.setAmountAvailable(cardsDto.getAvailableAmount());

        return cardEntity;
    }

}
