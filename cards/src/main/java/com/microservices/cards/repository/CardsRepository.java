package com.microservices.cards.repository;

import com.microservices.cards.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardsRepository extends JpaRepository<CardEntity, Long> {

     Optional<CardEntity> findByMobileNumber(String mobileNumber);
     Optional<CardEntity> findByCardNumber(String cardNumber);

}
