package com.project.ecomm.demo.exceptions;

import com.project.ecomm.demo.dtos.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // Normally spring return 200 default for all - even for exceptions
//    if you want custom status code then use ResponseEntity
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleGlobalProductNotFoundException(Exception e){
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setStatus("Failure");
        exceptionDTO.setMessage(e.getMessage());
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }
}
