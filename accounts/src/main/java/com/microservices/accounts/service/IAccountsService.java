package com.microservices.accounts.service;

import com.microservices.accounts.dto.CustomerDto;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);
    CustomerDto fetchAccount(String mobileNumber);
    boolean udpateAccount(CustomerDto customerDto);
    boolean deleteAccount(String mobileNumber);


}
