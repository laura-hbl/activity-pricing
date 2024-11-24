package com.activities.pricing.domain.exceptions;

public class InvalidPricingRequest extends RuntimeException {

    public InvalidPricingRequest(String message) {
        super(message);
    }
}
