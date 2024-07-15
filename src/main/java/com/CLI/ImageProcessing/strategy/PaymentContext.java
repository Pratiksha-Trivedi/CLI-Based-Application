package com.CLI.ImageProcessing.strategy;

public class PaymentContext {

    private PricingStrategy pricingStrategy;
    private PaymentStrategy paymentStrategy;

    public void setPricingStrategy(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment() {
        double amount = pricingStrategy.getPrice();
        paymentStrategy.executePayment(amount);
    }
}
