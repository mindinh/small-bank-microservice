package com.microservices.cards.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity(name = "cards")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cardId;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_type")
    private String cardType;

    @Column(name = "total_limit")
    private int totalLimit;

    @Column(name = "amount_used")
    private int amountUsed;

    @Column(name = "available_amount")
    private int amountAvailable;


}
