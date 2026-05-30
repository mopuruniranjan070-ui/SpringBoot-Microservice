package com.niranjan.commonlib.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class ErrorResponse {
    private String error;
    private String details;
    private int status;
    private LocalDateTime timestamp;

    public ErrorResponse(String error, String details, int status, LocalDateTime timestamp) {
        this.error = error;
        this.details = details;
        this.status = status;
        this.timestamp = timestamp;
    }



}
