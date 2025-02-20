package com.microservices.loans.service.impl;

import com.microservices.loans.constants.LoanConstants;
import com.microservices.loans.dto.LoanDto;
import com.microservices.loans.entity.LoanEntity;
import com.microservices.loans.exception.LoanAlreadyExistsException;
import com.microservices.loans.exception.ResourceNotFoundException;
import com.microservices.loans.mapper.LoansMapper;
import com.microservices.loans.repository.LoansRepository;
import com.microservices.loans.service.ILoansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoansServiceImpl implements ILoansService {

    @Autowired
    private LoansRepository loansRepository;


    @Override
    public void createLoans(String mobileNumber) {
        Optional<LoanEntity> loanEntityOptional = loansRepository.findByMobileNumber(mobileNumber);
        if (loanEntityOptional.isPresent()) {
            throw new LoanAlreadyExistsException(mobileNumber);
        }

        LoanEntity newLoan = new LoanEntity();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        loansRepository.save(newLoan);
    }

    @Override
    public LoanDto getLoanDetails(String mobileNumber) {
        LoanEntity loanEntity = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber)
        );
        return LoansMapper.mapToDto(loanEntity, new LoanDto());
    }

    @Override
    public boolean updateLoanDetails(LoanDto loanDto) {
        LoanEntity loanEntity = loansRepository.findByMobileNumber(loanDto.getMobileNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Mobile Number", loanDto.getMobileNumber())
        );

        LoansMapper.mapToEntity(loanDto, loanEntity);
        loansRepository.save(loanEntity);

        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        LoanEntity loanEntity = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "Mobile Number", mobileNumber)
        );

        loansRepository.deleteById(loanEntity.getLoanId());
        return true;
    }
}
