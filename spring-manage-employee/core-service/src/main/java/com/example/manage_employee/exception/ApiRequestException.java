package com.example.manage_employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiRequestException {

    @ExceptionHandler(MyCustomException.class)
    public ResponseEntity<GeneralException> mapException(MyCustomException mx){
        HttpStatus status = mx.getHttpStatus();
        String message = mx.getMessage();
        Integer code = mx.getCodeDefineFE();
        LocalDateTime dateTime = LocalDateTime.now();
        GeneralException generalException = new GeneralException(status,message, code,dateTime);
        return new ResponseEntity<>(generalException, HttpStatus.BAD_REQUEST);
    }

}
