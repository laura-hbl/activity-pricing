package com.activities.pricing.domain.usecases;

import com.activities.pricing.domain.entities.Activity;
import com.activities.pricing.domain.exception.InvalidPricingRequest;

import java.time.DayOfWeek;

public class OnDemandActivityPriceCalculator implements PriceCalculator {

    public double calculateActivityPrice(Activity activity, int adults, int children, DayOfWeek day) {

        int total = adults + children;

        if (total > 4) {
            throw new InvalidPricingRequest("Places are limit to 4");
        }

        return (adults * activity.getAdultPrice()) + (children * activity.getChildPrice());
    }
}
