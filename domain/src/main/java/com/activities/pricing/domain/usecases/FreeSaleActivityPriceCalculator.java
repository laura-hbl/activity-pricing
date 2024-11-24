package com.activities.pricing.domain.usecases;

import com.activities.pricing.domain.dtos.PriceRequestDto;
import com.activities.pricing.domain.entities.Activity;
import com.activities.pricing.domain.exceptions.InvalidPricingRequest;
import com.activities.pricing.domain.repositories.ActivityRepository;

import java.time.DayOfWeek;

public class FreeSaleActivityPriceCalculator implements PriceCalculator {

    private final ActivityRepository activityRepository;

    public FreeSaleActivityPriceCalculator(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public double calculateActivityPrice(PriceRequestDto priceRequestDto) {

        Activity activity = activityRepository.findByCode(priceRequestDto.getCode());
        DayOfWeek day = priceRequestDto.getDay();

        if (!activity.getOpenDays().contains(day)) {
            throw new InvalidPricingRequest(String.format("Free sale activity '%s' is not available on %s.", activity.getTitle(), day));
        }

        double price = activity.getAdultPrice() * priceRequestDto.getNumberOfAdults();

        int children = priceRequestDto.getNumberOfChildren();
        double childPrice = activity.getChildPrice();

        if (children < 2) {
            // If less than 2, then just child number * price
            price += childPrice * children;
        } else {
            // If greater than 2, then 2 * price
            price += childPrice * 2;
            // And the rest of the children reduced rate of price / 2
            price += (childPrice * (children - 2)) / 2;
        }

        return price;
    }
}
