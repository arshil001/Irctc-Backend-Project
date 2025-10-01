package com.substring.Irctc.exception;


import com.substring.Irctc.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handlerSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception){
        ErrorResponse response = new ErrorResponse(exception.getMessage(),"400",false);

        ResponseEntity responseEntity = new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        return responseEntity;


    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handlerExceptionMethodArgumentNotValidException(MethodArgumentNotValidException exception){

        Map<String, String> response = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error->{
            String message = error.getDefaultMessage();
            String field= ((FieldError)error).getField();
            response.put(message,field);
        });

        ResponseEntity<Map<String,String>> errorResponse = new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        return errorResponse;


    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerResourceNotFoundException(ResourceNotFoundException exception){
        ErrorResponse response = new ErrorResponse(exception.getMessage(),"404",false);

        ResponseEntity responseEntity = new ResponseEntity(response, HttpStatus.NOT_FOUND);
        return responseEntity;


    }
}
