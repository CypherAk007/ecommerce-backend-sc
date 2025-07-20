package com.project.ecomm.demo.exceptions;

import com.project.ecomm.demo.dtos.ExceptionDTO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NullPointerException.class)
    public ExceptionDTO handleGlobalNullPointerException(){
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setStatus("Failure");
        exceptionDTO.setMessage("NPE Has Occured!!");
        return exceptionDTO;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ExceptionDTO handleGlobalProductNotFoundException(Exception e){
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setStatus("Failure");
        exceptionDTO.setMessage(e.getMessage());
        return exceptionDTO;
    }
}
