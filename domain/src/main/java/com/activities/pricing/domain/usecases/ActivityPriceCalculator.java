package com.activities.pricing.domain.usecases;

import com.activities.pricing.domain.dtos.PriceRequestDto;
import com.activities.pricing.domain.dtos.PriceResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityPriceCalculator implements PriceCalculator {

    private final ActivityPriceCalculatorManager activityPriceCalculator;

    @Autowired
    public ActivityPriceCalculator(ActivityPriceCalculatorManager activityPriceCalculator) {
        this.activityPriceCalculator = activityPriceCalculator;
    }

    public PriceResponseDto calculateActivityPrice(PriceRequestDto priceRequestDto) {

        PriceCalculator calculator = activityPriceCalculator.getCalculator(priceRequestDto.getActivityType());

        return calculator.calculateActivityPrice(priceRequestDto);
    }
}
