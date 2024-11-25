package com.activities.pricing.domain.repositories;

import com.activities.pricing.domain.entities.Activity;
import com.activities.pricing.domain.entities.FreeSaleActivity;
import com.activities.pricing.domain.entities.OnDemandActivity;
import com.activities.pricing.domain.exceptions.InvalidPricingRequest;

import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// Impl can be in an infrastructure module but adding it here for this simple project
// A database could also replace this with a @Repository
public class InMemoryActivityRepository implements ActivityRepository {

    private final Map<String, Activity> activities;

    public InMemoryActivityRepository() {
        Activity freeSaleActivity = new FreeSaleActivity("00001", "Museum",
                new HashSet<>(List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY,
                        DayOfWeek.FRIDAY)), 20, 10);

        Activity onDemandActivity = new OnDemandActivity("00002", "Excursion",
                new HashSet<>(List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)),
                10, 10);

        activities = Map.of(
                freeSaleActivity.getCode(), freeSaleActivity,
                onDemandActivity.getCode(), onDemandActivity
        );
    }

    public Activity findByCode(String activityCode) {
        return Optional.ofNullable(activities.get(activityCode)).orElseThrow(
                () -> new InvalidPricingRequest("No activity found for code: " + activityCode));
    }
}
