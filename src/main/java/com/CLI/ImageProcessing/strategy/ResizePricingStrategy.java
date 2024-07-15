package com.CLI.ImageProcessing.strategy;

public class ResizePricingStrategy implements PricingStrategy {

    @Override
    public double getPrice() {
        return 20.0; // Rs 20 for resize operation
    }
}
