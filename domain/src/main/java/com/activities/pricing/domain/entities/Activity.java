package com.activities.pricing.domain.entities;

import java.time.DayOfWeek;
import java.util.Objects;
import java.util.Set;

public abstract class Activity {

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

    public abstract ActivityType getType();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity activity)) return false;

        return Double.compare(adultPrice, activity.adultPrice) == 0 && Double.compare(childPrice, activity.childPrice) == 0 && Objects.equals(code, activity.code) && Objects.equals(title, activity.title) && Objects.equals(openDays, activity.openDays);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(code);
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(openDays);
        result = 31 * result + Double.hashCode(adultPrice);
        result = 31 * result + Double.hashCode(childPrice);
        return result;
    }
}
