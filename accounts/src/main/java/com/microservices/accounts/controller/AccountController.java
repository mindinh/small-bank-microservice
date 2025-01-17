package com.microservices.accounts.controller;


import com.microservices.accounts.constants.AccountsConstant;
import com.microservices.accounts.dto.CustomerDto;
import com.microservices.accounts.dto.ResponseDto;
import com.microservices.accounts.service.IAccountsService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class AccountController {

    private IAccountsService iAccountsService;
    public AccountController(IAccountsService accountsService) {
        this.iAccountsService = accountsService;
    }


    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        iAccountsService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstant.STATUS_201, AccountsConstant.MESSAGE_201));
    }


    @GetMapping("/fetch")
    public ResponseEntity<?> getAccountDetails(@RequestParam
                                                   @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                   String mobileNumber) {
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);

        return ResponseEntity.ok(customerDto);

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.udpateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.ok(new ResponseDto(AccountsConstant.STATUS_201, AccountsConstant.MESSAGE_201));
        }
        return ResponseEntity.internalServerError().body(new ResponseDto(AccountsConstant.STATUS_500, AccountsConstant.MESSAGE_500));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAccount(@RequestParam
                                               @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                               String mobileNumber) {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.ok(new ResponseDto(AccountsConstant.STATUS_200, AccountsConstant.MESSAGE_200));
        }
        return ResponseEntity.internalServerError().body(new ResponseDto(AccountsConstant.STATUS_500, AccountsConstant.MESSAGE_500));

    }
}
