package org.example;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShoppingCartTest {

    @Test
    void addItem() {
        // setup
        ShippingService ss = new ShippingService(0.20, 0.15);
        Customer c = new Customer("c1", "Hunter", "70802");
        ShoppingCart cart = new ShoppingCart(c, ss);
        Product apple = new Product("p1", "Apple", "Fruit", 1.00);
        // execute
        cart.addItem(apple, 2);
        CartItem item = cart.getItems().get(0);
        // assert
        assertEquals(apple, item.getProduct());
        assertEquals(2, item.getQuantity());
    }

    @Test
    void getSubtotal() {
        // setup
        ShippingService ss = new ShippingService(0.20, 0.15);
        Customer c = new Customer("c1", "Hunter", "70802");
        ShoppingCart cart = new ShoppingCart(c, ss);
        Product apple = new Product("p1", "Apple", "Fruit", 1.00);
        // execute
        cart.addItem(apple, 2);
        // assert
        assertEquals(cart.getSubtotal(), 2.00);
    }

    @Test
    void getDiscountAmount() {
        // setup
        ShippingService ss = new ShippingService(0.20, 0.15);
        Customer c = new Customer("c1", "Hunter", "70802");
        ShoppingCart cart = new ShoppingCart(c, ss);
        Product apple = new Product("p1", "Apple", "Fruit", 1.00);
        // execute
        cart.addItem(apple, 2);
        c.addLoyaltyPoints(250);
        // assert
        assertEquals(cart.getDiscountAmount(), 0.2);
    }

    //Mock test
    @Test
    void getShippingCost() {
        // setup
        ShippingService mockShipping = mock(ShippingService.class);
        Customer c = new Customer("c1", "Hunter", "70802");
        ShoppingCart cart = new ShoppingCart(c, mockShipping);
        Product apple = new Product("p1", "Apple", "Fruit", 1.00);
        // execute
        cart.addItem(apple, 3);
        when(mockShipping.estimateShipping(c, 3)).thenReturn(9.99);
        // assert
        assertEquals(cart.getShippingCost(), 9.99);
    }

    @ParameterizedTest
    @MethodSource("generator")
    void getTotal(int loyaltyPoints, double expectedTotal) {
        // setup
        ShippingService ss = new ShippingService(0.20, 0.15);
        Customer c = new Customer("c1", "Hunter", "70802");
        c.addLoyaltyPoints(loyaltyPoints);
        ShoppingCart cart = new ShoppingCart(c, ss);
        Product apple = new Product("p1", "Apple", "Fruit", 1.00);
        // execute
        cart.addItem(apple, 2);
        // assert
        assertEquals(expectedTotal, cart.getTotal(), 0.0001);
    }

    private static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of(0,   2.50),
                Arguments.of(60,  2.40),
                Arguments.of(250, 2.30),
                Arguments.of(600, 2.20),
                Arguments.of(1000, 2.20)

        );
    }
}