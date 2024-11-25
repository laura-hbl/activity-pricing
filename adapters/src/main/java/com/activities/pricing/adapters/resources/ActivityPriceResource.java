package com.activities.pricing.adapters.resources;

public class ActivityPriceResource {

    private double price;

    public ActivityPriceResource(double price) {
        this.price = price;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityPriceResource that)) return false;

        return Double.compare(price, that.price) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(price);
    }
}
