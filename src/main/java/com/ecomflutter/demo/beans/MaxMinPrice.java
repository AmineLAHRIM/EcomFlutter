package com.ecomflutter.demo.beans;

public class MaxMinPrice {
    private double maxPrice;
    private double minPrice;

    public MaxMinPrice(double maxPrice, double minPrice) {
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }
}
