package de.ovgu.cs.vocab.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Inspired by: https://www.baeldung.com/exception-handling-for-rest-with-spring
 */
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);


    @ExceptionHandler(value = { Exception.class})
    protected ResponseEntity<String> handleConflict(
            RuntimeException ex, WebRequest request) {
        log.warn(ex.getMessage());
        if(ex instanceof ResponseStatusException){
            ResponseStatusException respEx = (ResponseStatusException) ex;
            return ResponseEntity.status(respEx.getStatus()).body(respEx.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("A fatal error occurred.");
    }
}
