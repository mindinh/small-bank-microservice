package com.microservices.cards.repository;

import com.microservices.cards.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardsRepository extends JpaRepository<CardEntity, Long> {

     Optional<CardEntity> findByMobileNumber(String mobileNumber);

}
