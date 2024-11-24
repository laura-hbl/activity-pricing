package com.activities.pricing.domain.usecases;

import com.activities.pricing.domain.dtos.PriceRequestDto;

public interface PriceCalculator {

    double calculateActivityPrice(PriceRequestDto priceRequestDto);
}

