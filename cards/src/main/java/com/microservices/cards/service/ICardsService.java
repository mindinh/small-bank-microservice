package com.microservices.cards.service;


import com.microservices.cards.dto.CardsDto;
import com.microservices.cards.entity.CardEntity;

import java.util.Optional;

public interface ICardsService {

    void createCard(String mobileNo);
    CardsDto fetchCardDetails(String mobileNo);

}
