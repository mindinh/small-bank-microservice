package com.microservices.loans.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponseDto {
    private String apiPath;

    private HttpStatus errorCode;

    private String errorMessage;

    private LocalDateTime errorTimestamp;

    public ErrorResponseDto(String apiPath, HttpStatus errorCode, String errorMessage, LocalDateTime errorTimestamp) {
        this.apiPath = apiPath;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorTimestamp = errorTimestamp;
    }
}