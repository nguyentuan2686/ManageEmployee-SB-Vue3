package com.example.manage_employee.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
public class MyCustomException extends RuntimeException{

    private HttpStatus httpStatus;
    private String message;
    private Integer codeDefineFE;


    public MyCustomException(String message, HttpStatus httpStatus, Integer codeDefineFE){
        this.message = message;
        this.httpStatus = httpStatus;
        this.codeDefineFE = codeDefineFE;
    }

}
