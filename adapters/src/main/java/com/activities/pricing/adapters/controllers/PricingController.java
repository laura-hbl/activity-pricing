package com.activities.pricing.adapters.controllers;

import com.activities.pricing.adapters.resources.ActivityPriceResource;
import com.activities.pricing.domain.dtos.PriceRequestDto;
import com.activities.pricing.domain.dtos.PriceResponseDto;
import com.activities.pricing.domain.usecases.PriceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activities")
public class PricingController {

    private final PriceCalculator priceCalculator;

    @Autowired
    public PricingController(PriceCalculator priceCalculator) {
        this.priceCalculator = priceCalculator;
    }

    @PostMapping("/price")
    public ResponseEntity<ActivityPriceResource> getActivityPrice(@RequestBody PriceRequestDto priceRequest) {

        PriceResponseDto priceDto = priceCalculator.calculateActivityPrice(priceRequest);

        return ResponseEntity.ok(new ActivityPriceResource(priceDto.getPrice()));
    }
}
