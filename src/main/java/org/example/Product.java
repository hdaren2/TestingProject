package org.example;

import java.util.Objects;

public class Product {
    private final String id;
    private final String name;
    private final String category;
    private final double price;

    public Product(String id, String name, String category, double price) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name");
        if (price < 0) throw new IllegalArgumentException("price");
        this.id = id;
        this.name = name;
        this.category = category == null ? "uncategorized" : category;
        this.price = price;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    public Product withPrice(double newPrice) {
        return new Product(id, name, category, newPrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

