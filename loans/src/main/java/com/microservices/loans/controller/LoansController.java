package com.microservices.loans.controller;


import com.microservices.loans.constants.LoanConstants;
import com.microservices.loans.dto.LoanDto;
import com.microservices.loans.dto.ResponseDto;
import com.microservices.loans.service.ILoansService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class LoansController {

    private ILoansService loansService;
    public LoansController(ILoansService loansService) {
        this.loansService = loansService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createLoan(@Valid @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                            String mobileNumber) {
        loansService.createLoans(mobileNumber);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(LoanConstants.STATUS_201, LoanConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> getLoanDetails(@Valid @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                String mobileNumber) {
        LoanDto loanDto = loansService.getLoanDetails(mobileNumber);
        if (loanDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(loanDto);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Loan Details not found under " + mobileNumber);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateLoan(@Valid @RequestBody LoanDto loanDto) {
        boolean isUpdated = loansService.updateLoanDetails(loanDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteLoan(@Valid @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                            String mobileNumber) {

        boolean isDeleted = loansService.deleteLoan(mobileNumber);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(LoanConstants.STATUS_200, LoanConstants.MESSAGE_200));
        }
        else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseDto(LoanConstants.STATUS_417, LoanConstants.MESSAGE_417_DELETE));
        }
    }

}
