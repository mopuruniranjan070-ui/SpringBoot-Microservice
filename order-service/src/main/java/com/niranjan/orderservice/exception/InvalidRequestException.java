package com.niranjan.orderservice.exception;

import org.apache.kafka.common.errors.ApiException;

public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}


