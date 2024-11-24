package com.activities.pricing.domain.dtos;

public class PriceResponseDto {

    private double price;

    public PriceResponseDto(double price) {
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
        if (!(o instanceof PriceResponseDto that)) return false;

        return Double.compare(price, that.price) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(price);
    }

    @Override
    public String toString() {
        return "PriceResponseDto{" +
                "price=" + price +
                '}';
    }
}
