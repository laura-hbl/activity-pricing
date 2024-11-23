package com.activities.pricing.domain.dtos;

import com.activities.pricing.domain.entities.ActivityType;

import java.time.DayOfWeek;
import java.util.Objects;

public class PriceRequestDto {

    private final String code;
    private final DayOfWeek day;
    private final ActivityType activityType;
    private final int numberOfAdults;
    private final int numberOfChildren;

    public PriceRequestDto(String code, DayOfWeek day, ActivityType activityType, int numberOfAdults, int numberOfChildren) {
        this.code = code;
        this.day = day;
        this.activityType = activityType;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
    }

    public String getCode() {
        return code;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceRequestDto that)) return false;

        return numberOfAdults == that.numberOfAdults && numberOfChildren == that.numberOfChildren && Objects.equals(code, that.code) && day == that.day && activityType == that.activityType;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(code);
        result = 31 * result + Objects.hashCode(day);
        result = 31 * result + Objects.hashCode(activityType);
        result = 31 * result + numberOfAdults;
        result = 31 * result + numberOfChildren;
        return result;
    }

    @Override
    public String toString() {
        return "PriceRequestDto{" +
                "code='" + code + '\'' +
                ", day=" + day +
                ", activityType=" + activityType +
                ", numberOfAdults=" + numberOfAdults +
                ", numberOfChildren=" + numberOfChildren +
                '}';
    }
}
