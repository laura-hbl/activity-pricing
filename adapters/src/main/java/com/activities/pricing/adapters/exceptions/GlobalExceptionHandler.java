package com.activities.pricing.adapters.exceptions;

import com.activities.pricing.domain.exceptions.InvalidPricingRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidPricingRequest.class)
    public ResponseEntity<String> handleInvalidPricingRequest(InvalidPricingRequest ex) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}