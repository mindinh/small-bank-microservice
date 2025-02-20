package com.microservices.loans.repository;

import com.microservices.loans.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LoansRepository extends JpaRepository<LoanEntity, Long> {
    Optional<LoanEntity> findByMobileNumber(String mobileNumber);

}
