package com.activities.pricing.domain.usecases;

import com.activities.pricing.domain.dtos.PriceRequestDto;
import com.activities.pricing.domain.dtos.PriceResponseDto;
import com.activities.pricing.domain.entities.Activity;
import com.activities.pricing.domain.exceptions.InvalidPricingRequest;
import com.activities.pricing.domain.repositories.ActivityRepository;

import static com.activities.pricing.domain.util.ActivityRulesValidator.validateOpenDay;
import static com.activities.pricing.domain.util.ActivityRulesValidator.validateParticipantNumbers;

public class OnDemandActivityPriceCalculator implements PriceCalculator {

    private final ActivityRepository activityRepository;

    public OnDemandActivityPriceCalculator(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public PriceResponseDto calculateActivityPrice(PriceRequestDto priceRequestDto) {

        Activity activity = activityRepository.findByCode(priceRequestDto.getCode());
        validateOpenDay(activity, priceRequestDto.getDay());
        validateParticipantNumbers(priceRequestDto.getNumberOfAdults(), priceRequestDto.getNumberOfChildren());

        int adults = priceRequestDto.getNumberOfAdults();
        int children = priceRequestDto.getNumberOfChildren();

        if (adults + children > 4) {
            throw new InvalidPricingRequest("Places are limit to 4");
        }

        double price = (adults * activity.getAdultPrice()) + (children * activity.getChildPrice());
        return new PriceResponseDto(price);
    }
}
