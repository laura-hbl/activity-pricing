package com.activities.pricing.domain.usecases;

import com.activities.pricing.domain.entities.ActivityType;
import com.activities.pricing.domain.repositories.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ActivityPriceCalculatorManager {

    private final Map<ActivityType, PriceCalculator> calculators;

    @Autowired
    public ActivityPriceCalculatorManager(ActivityRepository activityRepository) {
        calculators = Map.of(
                ActivityType.ON_DEMAND, new OnDemandActivityPriceCalculator(activityRepository),
                ActivityType.FREE_SALE, new FreeSaleActivityPriceCalculator(activityRepository)
        );
    }

    public PriceCalculator getCalculator(ActivityType activityType) {
        return calculators.get(activityType);
    }
}
