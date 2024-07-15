package com.CLI.ImageProcessing.strategy;

public class PayPalPaymentStrategy implements PaymentStrategy {

    @Override
    public void executePayment(double amount) {
        // Implement PayPal payment logic here
        System.out.println("Paying Rs " + amount + " via PayPal.");
    }
}
