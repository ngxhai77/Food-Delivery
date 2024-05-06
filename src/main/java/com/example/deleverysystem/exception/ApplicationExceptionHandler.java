package com.example.deleverysystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.example.deleverysystem.exception.ErrorMessage;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @ExceptionHandler(ErrorMessage.class)
    public ResponseEntity<String> handleItemNotFoundException(ErrorMessage ex){
        return new ResponseEntity<>(ex.getMessage(), ex.getStatusCode());
    }
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<String> handleUnauthorizedException(HttpClientErrorException.Unauthorized ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Status: " + ex.getStatusCode());
    }

}