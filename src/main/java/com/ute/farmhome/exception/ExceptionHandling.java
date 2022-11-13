package com.ute.farmhome.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity handlerNotFound(Exception e, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(value = ResourceNotFound.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity ResourceNotFound(ResourceNotFound ex, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(ex.toString());
    }
}
