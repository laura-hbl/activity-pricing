package com.activities.pricing.domain.usecases;

import com.activities.pricing.domain.dtos.PriceRequestDto;
import com.activities.pricing.domain.dtos.PriceResponseDto;
import com.activities.pricing.domain.entities.Activity;
import com.activities.pricing.domain.exceptions.InvalidPricingRequest;
import com.activities.pricing.domain.repositories.ActivityRepository;

import java.time.DayOfWeek;

public class OnDemandActivityPriceCalculator implements PriceCalculator {

    private final ActivityRepository activityRepository;

    public OnDemandActivityPriceCalculator(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public PriceResponseDto calculateActivityPrice(PriceRequestDto priceRequestDto) {

        Activity activity = activityRepository.findByCode(priceRequestDto.getCode());
        DayOfWeek day = priceRequestDto.getDay();

        if (!activity.getOpenDays().contains(day)) {
            throw new InvalidPricingRequest(String.format("On demand activity '%s' is not available on %s.", activity.getTitle(), day));
        }

        int adults = priceRequestDto.getNumberOfAdults();
        int children = priceRequestDto.getNumberOfChildren();
        int total = adults + children;

        if (total > 4) {
            throw new InvalidPricingRequest("Places are limit to 4");
        }

        double price = (adults * activity.getAdultPrice()) + (children * activity.getChildPrice());

        return new PriceResponseDto(price);
    }
}
