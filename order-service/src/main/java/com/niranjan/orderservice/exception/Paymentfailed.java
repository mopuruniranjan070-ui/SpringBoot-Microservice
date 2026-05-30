package com.niranjan.orderservice.exception;

public class Paymentfailed extends RuntimeException {
    public Paymentfailed(String message) {
        super(message);
    }
}
