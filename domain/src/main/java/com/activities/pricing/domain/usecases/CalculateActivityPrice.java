package com.activities.pricing.domain.usecases;

import com.activities.pricing.domain.entities.Activity;

import java.time.DayOfWeek;

public class CalculateActivityPrice {

    public double calculateFreeSaleActivityPrice(Activity activity, int adults, int children, DayOfWeek day) {

        if(!activity.getOpenDays().contains(day)) {
           return -1;
        }

        double price = activity.getAdultPrice() * adults;
        for (int i = 1; i <= children; i++) {
            if (i <= 2) {
                price += activity.getChildPrice();
            } else {
                price += activity.getChildPrice() / 2;
            }
        }
        return price;
    }
}
