package com.activities.pricing.adapters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "com.activities.pricing"
})
public class ActivityPricingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivityPricingApplication.class, args);
    }
}
