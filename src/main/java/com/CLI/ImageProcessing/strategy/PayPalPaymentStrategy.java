package com.CLI.ImageProcessing.strategy;
public class PayPalPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paying " + amount + " via PayPal");
    }
}