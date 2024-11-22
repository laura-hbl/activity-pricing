package com.activities.pricing.domain.entities;

import java.time.DayOfWeek;
import java.util.Set;

public class Activity {

    private final String code;
    private final String title;
    private final Set<DayOfWeek> openDays;
    private final double adultPrice;
    private final double childPrice;

    public Activity(String code, String title, Set<DayOfWeek> openDays,
                    double adultPrice, double childPrice) {
        this.code = code;
        this.title = title;
        this.openDays = openDays;
        this.adultPrice = adultPrice;
        this.childPrice = childPrice;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public Set<DayOfWeek> getOpenDays() {
        return openDays;
    }

    public double getAdultPrice() {
        return adultPrice;
    }

    public double getChildPrice() {
        return childPrice;
    }
}
