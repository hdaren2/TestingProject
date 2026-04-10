package org.example;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {

    @Test
    void getProduct() {
        //setup
        //execute
        Product apple = new Product("p1", "Apple", "Fruit", 1.00);
        CartItem item = new CartItem(apple, 1);
        //assert
        assertEquals(apple, item.getProduct());
    }

    @Test
    void getQuantity() {
        //setup
        //execute
        Product product = new Product("p1", "Apple", "Fruit", 1.00);
        CartItem item = new CartItem(product, 1);
        //assert
        assertEquals(1, item.getQuantity());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5, 10})
    void changeQuantity(int newQuantity) {
        //setup
        Product apple = new Product("p1", "Apple", "Fruit", 1.00);
        CartItem item = new CartItem(apple, 1);
        //execute
        item.changeQuantity(newQuantity);
        //assert
        assertEquals(newQuantity, item.getQuantity());
    }

    @Property
    void lineTotal(
            @ForAll @DoubleRange(min = 0.0, max = 1000.0) double price,
            @ForAll @IntRange(min = 1, max = 1000) int quantity
    ) {
        //setup
        Product apple = new Product("p1", "Apple", "Fruit", price);
        CartItem item = new CartItem(apple, quantity);
        //execute
        double total = item.getLineTotal();
        //assert
        assertEquals(price * quantity, total, 0.0001);
        assertTrue(total >= 0.0);
    }
}