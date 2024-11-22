package com.activities.pricing.domain.entities;

import java.time.DayOfWeek;
import java.util.Set;

public class OnDemandActivity extends Activity {

    public OnDemandActivity(String code, String title, Set<DayOfWeek> openDays, double adultPrice, double childPrice) {
        super(code, title, openDays, adultPrice, childPrice);
    }
}
