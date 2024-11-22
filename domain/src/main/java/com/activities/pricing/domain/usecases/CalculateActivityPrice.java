package com.activities.pricing.domain.usecases;

import com.activities.pricing.domain.entities.Activity;
import com.activities.pricing.domain.exception.InvalidPricingRequest;

import java.time.DayOfWeek;

public class CalculateActivityPrice {

    public double calculateFreeSaleActivityPrice(Activity activity, int adults, int children, DayOfWeek day) {

        checkActivityAvailability(activity, day);

        double price = activity.getAdultPrice() * adults;

        if (children < 2) {
            // If less than 2, then just child number * price
            price += activity.getChildPrice() * children;
        } else {
            // If greater than 2, then 2 * price
            price += activity.getChildPrice() * 2;
            // And the rest of the children reduced rate of price / 2
            price += (activity.getChildPrice() * (children - 2)) / 2;
        }

        return price;
    }

    public double calculateOnDemandActivityPrice(Activity activity, int adults, int children, DayOfWeek day) {

        checkActivityAvailability(activity, day);

        int total = adults + children;

        if (total > 4) {
            throw new InvalidPricingRequest("Places are limit to 4");
        }

        return (adults * activity.getAdultPrice()) + (children * activity.getChildPrice());
    }

    private void checkActivityAvailability(Activity activity, DayOfWeek day) {

        if (!activity.getOpenDays().contains(day)) {
            throw new InvalidPricingRequest("Activity not available on %s".formatted(day));
        }
    }
}
