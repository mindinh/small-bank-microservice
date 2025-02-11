package com.microservices.cards.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ResponseDto {
    private String statusCode;
    private String statusMessage;

    public ResponseDto(String statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }
}
