package com.activities.pricing.domain.usecases;

import com.activities.pricing.domain.entities.Activity;

import java.time.DayOfWeek;

public interface PriceCalculator {

    double calculateActivityPrice(Activity activity, int adults, int children, DayOfWeek day);
}

