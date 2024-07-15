package com.CLI.ImageProcessing.strategy;

public class ExtendPricingStrategy implements PricingStrategy {

    @Override
    public double getPrice() {
        return 30.0; // Rs 30 for extend operation
    }
}
