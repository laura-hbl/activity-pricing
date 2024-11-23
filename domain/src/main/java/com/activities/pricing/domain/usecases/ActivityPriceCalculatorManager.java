package com.activities.pricing.domain.usecases;

import java.util.Map;

public class ActivityPriceCalculatorManager {

    private static String FREE_SALE_CODE = "45221";
    private static String ON_DEMAND_CODE = "13121";

    public static Map<String, PriceCalculator> calculators;

    public ActivityPriceCalculatorManager() {
        calculators = Map.of(
                ON_DEMAND_CODE, new OnDemandActivityPriceCalculator(),
                FREE_SALE_CODE, new FreeSaleActivityPriceCalculator()
        );
    }

    public PriceCalculator getCalculator(String code) {

        return calculators.get(code);
    }
}
