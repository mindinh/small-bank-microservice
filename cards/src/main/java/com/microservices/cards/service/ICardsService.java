package com.microservices.cards.service;

import com.microservices.cards.dto.CardsDto;
import org.springframework.stereotype.Service;

import javax.smartcardio.Card;

@Service
public interface ICardsService {

    void createCard(String mobileNo);

}
