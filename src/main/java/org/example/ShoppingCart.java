package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingCart {
    private final Customer customer;
    private final ShippingService shippingService;
    private final List<CartItem> items = new ArrayList<>();

    public ShoppingCart(Customer customer, ShippingService shippingService) {
        if (customer == null || shippingService == null) {
            throw new IllegalArgumentException("customer/shippingService");
        }
        this.customer = customer;
        this.shippingService = shippingService;
    }

    public void addItem(Product product, int quantity) {
        items.add(new CartItem(product, quantity));
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getLineTotal).sum();
    }

    public double getDiscountAmount() {
        return getSubtotal() * customer.getDiscountRate();
    }

    public double getShippingCost() {
        int totalItems = items.stream().mapToInt(CartItem::getQuantity).sum();
        return shippingService.estimateShipping(customer, totalItems);
    }

    public double getTotal() {
        return getSubtotal() - getDiscountAmount() + getShippingCost();
    }

}
