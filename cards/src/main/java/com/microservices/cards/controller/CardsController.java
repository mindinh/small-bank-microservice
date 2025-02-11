package com.microservices.cards.controller;


import com.microservices.cards.constants.CardConstants;
import com.microservices.cards.dto.ResponseDto;
import com.microservices.cards.service.ICardsService;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CardsController {

    private ICardsService iCardsService;
    public CardsController(ICardsService cardsService) {
        this.iCardsService = cardsService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCard(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                            String mobileNumber) {

        iCardsService.createCard(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(CardConstants.STATUS_201, CardConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> fetchCardDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                  String mobileNumber) {

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(CardConstants.STATUS_200, CardConstants.MESSAGE_200));
    }

}
