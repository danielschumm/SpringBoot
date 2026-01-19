package com.daniels.tickets;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.NoSuchElementException;
import org.springframework.web.ErrorResponse;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResponse notFound(NoSuchElementException ex) {
        return ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
