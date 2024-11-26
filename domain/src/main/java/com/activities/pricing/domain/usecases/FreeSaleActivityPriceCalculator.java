package com.activities.pricing.domain.usecases;

import com.activities.pricing.domain.dtos.PriceRequestDto;
import com.activities.pricing.domain.dtos.PriceResponseDto;
import com.activities.pricing.domain.entities.Activity;
import com.activities.pricing.domain.repositories.ActivityRepository;

import static com.activities.pricing.domain.util.ActivityRulesValidator.validateOpenDay;
import static com.activities.pricing.domain.util.ActivityRulesValidator.validateParticipantNumbers;

public class FreeSaleActivityPriceCalculator implements PriceCalculator {

    private final ActivityRepository activityRepository;

    public FreeSaleActivityPriceCalculator(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public PriceResponseDto calculateActivityPrice(PriceRequestDto priceRequestDto) {

        Activity activity = activityRepository.findByCode(priceRequestDto.getCode());

        int children = priceRequestDto.getNumberOfChildren();
        int adults = priceRequestDto.getNumberOfAdults();

        validateOpenDay(activity, priceRequestDto.getDay());
        validateParticipantNumbers(adults, children);

        double price = activity.getAdultPrice() * adults;
        double childPrice = activity.getChildPrice();

        if (children < 2) {
            price += childPrice * children;
        } else {
            price += childPrice * 2; // Full price for first two children
            price += (childPrice * (children - 2)) / 2; // Discount for remaining
        }

        return new PriceResponseDto(price);
    }
}
