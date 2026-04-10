package org.example;

public class ShippingService {
    private final double baseRate;
    private final double perItemRate;

    public ShippingService(double baseRate, double perItemRate) {
        if (baseRate < 0 || perItemRate < 0) {
            throw new IllegalArgumentException("rates");
        }
        this.baseRate = baseRate;
        this.perItemRate = perItemRate;
    }

    public double estimateShipping(Customer customer, int totalItems) {
        if (totalItems < 0) throw new IllegalArgumentException("totalItems");
        double cost = baseRate + perItemRate * totalItems;
        if (customer.getZipCode().startsWith("9")) {
            // pretend "9xxx" zip codes are remote and more expensive
            cost *= 1.2;
        }
        return Math.round(cost * 100.0) / 100.0;
    }
}

