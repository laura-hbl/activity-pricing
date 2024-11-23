package com.activities.pricing.domain.usecases;

import com.activities.pricing.domain.entities.Activity;

import java.time.DayOfWeek;

public class FreeSaleActivityPriceCalculator implements PriceCalculator {

    public double calculateActivityPrice(Activity activity, int adults, int children, DayOfWeek day) {

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
}
