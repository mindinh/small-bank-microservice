package com.microservices.accounts.mapper;

import com.microservices.accounts.dto.AccountsDto;
import com.microservices.accounts.entity.AccountEntity;

public class AccountsMapper {

    public static AccountsDto mapToAccountsDto(AccountEntity accounts, AccountsDto accountsDto) {
        accountsDto.setAccountNumber(accounts.getAccountNumber());
        accountsDto.setAccountType(accounts.getAccountType());
        accountsDto.setBranchAddresss(accounts.getBranchAddresss());
        return accountsDto;
    }

    public static AccountEntity mapToAccounts(AccountsDto accountsDto, AccountEntity accounts) {
        accounts.setAccountNumber(accountsDto.getAccountNumber());
        accounts.setAccountType(accountsDto.getAccountType());
        accounts.setBranchAddresss(accountsDto.getBranchAddresss());
        return accounts;
    }

}
