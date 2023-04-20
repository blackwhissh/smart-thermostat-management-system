package com.blackwhissh.smartthermostat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ThermostatNotFoundException.class)
    public ResponseEntity<Object> handleExceptions(ThermostatNotFoundException exception, WebRequest webRequest){
        ExceptionResponse response = new ExceptionResponse();
        response.setDateTime(LocalDateTime.now());
        response.setMessage("Thermostat not found");
        ResponseEntity<Object> entity = new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        return entity;
    }
}
