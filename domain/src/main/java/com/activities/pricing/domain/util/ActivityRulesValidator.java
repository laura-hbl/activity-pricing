package com.activities.pricing.domain.util;

import com.activities.pricing.domain.entities.Activity;
import com.activities.pricing.domain.exceptions.InvalidPricingRequest;

import java.time.DayOfWeek;

public class ActivityRulesValidator {

    public static void validateOpenDay(Activity activity, DayOfWeek day) {
        if (!activity.getOpenDays().contains(day)) {
            throw new InvalidPricingRequest(
                    String.format("Activity '%s' is not available on %s.", activity.getTitle(), day));
        }
    }

    public static void validateParticipantNumbers(int adults, int children) {
        if (adults < 0 || children < 0) {
            throw new InvalidPricingRequest("The number of participants cannot be negative.");
        }
    }
}
