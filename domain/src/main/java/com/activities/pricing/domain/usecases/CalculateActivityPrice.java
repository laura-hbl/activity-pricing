package com.activities.pricing.domain.usecases;

import com.activities.pricing.domain.entities.Activity;
import com.activities.pricing.domain.exception.InvalidPricingRequest;

import java.time.DayOfWeek;

public class CalculateActivityPrice {

    private final ActivityPriceCalculatorManager activityPriceCalculator;

    public CalculateActivityPrice(ActivityPriceCalculatorManager activityPriceCalculator) {
        this.activityPriceCalculator = activityPriceCalculator;
    }

    public double calculateActivityPrice(Activity activity, int adults, int children, DayOfWeek day) {

        if (!activity.getOpenDays().contains(day)) {
            throw new InvalidPricingRequest(String.format("Activity '%s' is not available on %s.", activity.getTitle(), day));
        }
        PriceCalculator calculator = activityPriceCalculator.getCalculator(activity.getCode());

        if (calculator == null) {
            throw new InvalidPricingRequest("No calculator found for code: " + activity.getCode());
        }

        return calculator.calculateActivityPrice(activity, adults, children, day);
    }
}
