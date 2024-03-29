package com.rinhabackend.rinhadebackend2024q1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ClienteNotFoundException.class)
  public ResponseEntity<Void> handleClienteNotFound(ClienteNotFoundException ex) {
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({ ClienteLimitExceededException.class, ValidationException.class,
      MethodArgumentNotValidException.class })
  public ResponseEntity<Void> handleClienteLimitExceeded(Exception ex) {
    return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
  }
}
