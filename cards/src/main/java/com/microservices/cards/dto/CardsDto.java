package com.microservices.cards.dto;


import lombok.Data;

@Data
public class CardsDto {
    private String cardNumber;
    private String cardType;
    private String mobileNumber;
    private int totalLimit;
    private int availableAmount;
}
