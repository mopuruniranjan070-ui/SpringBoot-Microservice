package com.niranjan.orderservice.exception;

import com.niranjan.commonlib.Dto.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Orderisnotfound.class)
    public ResponseEntity<ErrorResponse> orderisnotfoundException(Orderisnotfound fg){
        ErrorResponse response = new ErrorResponse("Order not found",fg.getMessage(),HttpStatus.BAD_REQUEST.value(),LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    };

   @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponse>invalidRequestException(InvalidRequestException ag){
        ErrorResponse response = new ErrorResponse("InvalidRequest",ag.getMessage(),HttpStatus.BAD_REQUEST.value(),LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Paymentfailed.class)
    public ResponseEntity<ErrorResponse> PaymentFailed(Paymentfailed Pay){
       ErrorResponse response = new ErrorResponse("Paymentfailed",Pay.getMessage(),HttpStatus.BAD_REQUEST.value(),LocalDateTime.now());
       return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
public ResponseEntity<ErrorResponse> handlegeneric(Exception hm){
    ErrorResponse response = new ErrorResponse("InvalidRequest",hm.getMessage(),HttpStatus.BAD_REQUEST.value(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
}

}
