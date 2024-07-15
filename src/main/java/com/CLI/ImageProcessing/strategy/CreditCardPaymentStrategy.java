package com.CLI.ImageProcessing.strategy;

public class CreditCardPaymentStrategy implements PaymentStrategy {

    @Override
    public void executePayment(double amount) {
        // Implement credit card payment logic here
        System.out.println("Paying Rs " + amount + " via Credit Card.");
    }
}
