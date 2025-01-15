package com.microservices.accounts.controller;


import com.microservices.accounts.constants.AccountsConstant;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.dto.ResponseDto;
import com.microservices.accounts.service.IAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountController {

    private IAccountsService iAccountsService;
    public AccountController(IAccountsService accountsService) {
        this.iAccountsService = accountsService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstant.STATUS_201, AccountsConstant.MESSAGE_201));
    }


    @GetMapping("/fetch")
    public ResponseEntity<?> getAccountDetails(@RequestParam String mobileNumber) {
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);

        return ResponseEntity.ok(customerDto);

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAccount(@RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.udpateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.ok(new ResponseDto(AccountsConstant.STATUS_201, AccountsConstant.MESSAGE_201));
        }
        return ResponseEntity.internalServerError().body(new ResponseDto(AccountsConstant.STATUS_500, AccountsConstant.MESSAGE_500));
    }
}
