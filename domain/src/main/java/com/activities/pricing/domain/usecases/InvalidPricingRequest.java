package com.activities.pricing.domain.usecases;

public class InvalidPricingRequest extends RuntimeException {
    public InvalidPricingRequest(String message) {
        super(message);
    }
}
