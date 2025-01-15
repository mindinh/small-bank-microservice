package com.microservices.accounts.service.impl;


import com.microservices.accounts.constants.AccountsConstant;
import com.microservices.accounts.dto.AccountsDto;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.entity.AccountEntity;
import com.microservices.accounts.entity.CustomerEntity;
import com.microservices.accounts.exception.CustomerAlreadyExistException;
import com.microservices.accounts.exception.ResourceNotFoundException;
import com.microservices.accounts.mapper.AccountsMapper;
import com.microservices.accounts.mapper.CustomersMapper;
import com.microservices.accounts.repository.AccountRepository;
import com.microservices.accounts.repository.CustomerRepository;
import com.microservices.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        CustomerEntity customerEntity = CustomersMapper.mapToCustomer(customerDto, new CustomerEntity());
        Optional<CustomerEntity> optionalCustomer = customerRepository.findByMobileNo(customerDto.getMobileNo());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistException("Customer already exist with this given mobile number" + customerDto.getMobileNo());

        }

        customerEntity.setCreatedAt(LocalDateTime.now());
        customerEntity.setCreatedBy("test");
        CustomerEntity savedCustomer = customerRepository.save(customerEntity);

        accountRepository.save(createNewAccount(savedCustomer));

    }

    private AccountEntity createNewAccount(CustomerEntity customer) {
        AccountEntity newAccount = new AccountEntity();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstant.SAVINGS);
        newAccount.setBranchAddresss(AccountsConstant.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("test");
        return newAccount;
    }


    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        CustomerEntity customerEntity = customerRepository.findByMobileNo(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber)
        );
        AccountEntity accountEntity = accountRepository.findByCustomerId(customerEntity.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "Customer id", customerEntity.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomersMapper.mapToCustomerDto(customerEntity, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accountEntity, new AccountsDto()));

        return customerDto;

    }

    @Override
    public boolean udpateAccount(CustomerDto customerDto) {
        boolean isSuccess = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            AccountEntity accountEntity = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "Account Number", accountsDto.getAccountNumber().toString())
            );
            AccountsMapper.mapToAccounts(accountsDto, accountEntity);
            accountEntity = accountRepository.save(accountEntity);

            Long customerId = accountEntity.getCustomerId();
            CustomerEntity customerEntity = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "Customer id", customerId.toString())
            );
            CustomersMapper.mapToCustomer(customerDto, customerEntity);
            customerRepository.save(customerEntity);
            isSuccess = true;

        }

        return isSuccess;
    }
}
