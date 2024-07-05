package com.CLI.ImageProcessing.strategy;
public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " via Credit Card");
    }
}
