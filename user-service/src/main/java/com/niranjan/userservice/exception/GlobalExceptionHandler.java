package com.niranjan.userservice.exception;

import com.niranjan.commonlib.Dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernotException(UserNotFoundException df) {
       ErrorResponse response = new ErrorResponse("UserNotFoundException",df.getMessage(),HttpStatus.NOT_FOUND.value(),null);
       return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception df) {
     ErrorResponse response = new ErrorResponse("Exception",df.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR.value(),null);
        return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);

    }
}
