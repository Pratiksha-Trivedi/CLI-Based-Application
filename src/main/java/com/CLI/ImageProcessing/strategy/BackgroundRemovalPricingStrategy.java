package com.CLI.ImageProcessing.strategy;

public class BackgroundRemovalPricingStrategy implements PricingStrategy {

    @Override
    public double getPrice() {
        return 50.0; // Rs 50 for background removal operation
    }
}
