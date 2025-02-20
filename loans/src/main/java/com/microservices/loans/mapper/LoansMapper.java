package com.microservices.loans.mapper;

import com.microservices.loans.dto.LoanDto;
import com.microservices.loans.entity.LoanEntity;

public class LoansMapper {
    public static LoanDto mapToDto(LoanEntity loanEntity, LoanDto loanDto) {
        loanDto.setLoanNumber(loanEntity.getLoanNumber());
        loanDto.setLoanType(loanEntity.getLoanType());
        loanDto.setTotalLoan(loanEntity.getTotalLoan());
        loanDto.setMobileNumber(loanEntity.getMobileNumber());
        loanDto.setAmountPaid(loanEntity.getAmountPaid());
        loanDto.setOutstandingAmount(loanEntity.getOutstandingAmount());

        return loanDto;
    }

    public static LoanEntity mapToEntity(LoanDto loanDto, LoanEntity loanEntity) {
        loanEntity.setLoanNumber(loanDto.getLoanNumber());
        loanEntity.setLoanType(loanDto.getLoanType());
        loanEntity.setTotalLoan(loanDto.getTotalLoan());
        loanEntity.setMobileNumber(loanDto.getMobileNumber());
        loanEntity.setAmountPaid(loanDto.getAmountPaid());
        loanEntity.setOutstandingAmount(loanDto.getOutstandingAmount());

        return loanEntity;
    }
}
