package com.microservices.loans.service;


import com.microservices.loans.dto.LoanDto;

public interface ILoansService {
    void createLoans(String mobileNumber);
    LoanDto getLoanDetails(String mobileNumber);
    boolean updateLoanDetails(LoanDto loanDto);
    boolean deleteLoan(String mobileNumber);

}
