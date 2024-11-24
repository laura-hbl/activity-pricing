package com.activities.pricing.domain.usecases;

import com.activities.pricing.domain.dtos.PriceRequestDto;
import com.activities.pricing.domain.dtos.PriceResponseDto;

public interface PriceCalculator {

    PriceResponseDto calculateActivityPrice(PriceRequestDto priceRequestDto);
}

