package com.example.manage_employee.exception;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class GeneralException {
    private HttpStatus httpStatus;
    private String errorMessage;
    private Integer errorCode;

    private LocalDateTime timestamp;

    public GeneralException(HttpStatus httpStatus, String errorMessage, Integer errorCode, LocalDateTime timestamp){
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.timestamp = timestamp;
    }
}
