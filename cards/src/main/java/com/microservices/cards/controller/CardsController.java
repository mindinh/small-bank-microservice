package com.microservices.cards.controller;


import com.microservices.cards.constants.CardConstants;
import com.microservices.cards.dto.ResponseDto;
import com.microservices.cards.service.ICardsService;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.smartcardio.Card;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardsController {

    private ICardsService cardsService;
    public CardsController(ICardsService cardsService) {
        this.cardsService = cardsService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCard(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                            String mobileNumber) {

        cardsService.createCard(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }

}
