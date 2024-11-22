package com.activities.pricing.domain.exception;

public class InvalidPricingRequest extends RuntimeException {

    public InvalidPricingRequest(String message) {
        super(message);
    }
}
