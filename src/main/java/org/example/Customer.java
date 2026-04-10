package org.example;

public class Customer {
    private final String id;
    private final String name;
    private final String zipCode;
    private int loyaltyPoints;

    public Customer(String id, String name, String zipCode) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name");
        this.id = id;
        this.name = name;
        this.zipCode = zipCode == null ? "" : zipCode;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getZipCode() { return zipCode; }
    public int getLoyaltyPoints() { return loyaltyPoints; }

    public void addLoyaltyPoints(int points) {
        if (points < 0) throw new IllegalArgumentException("points");
        loyaltyPoints += points;
    }

    public double getDiscountRate() {
        if (loyaltyPoints >= 500) return 0.15;
        if (loyaltyPoints >= 200) return 0.10;
        if (loyaltyPoints >= 50) return 0.05;
        return 0.0;
    }
}
