package org.example;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void getId() {
        //setup
        //execute
        Product apple = new Product("p1", "Apple", "Fruit", 1.00);
        //assert
        assertEquals("p1", apple.getId());
    }

    @Test
    void getName() {
        //setup
        //execute
        Product apple = new Product("p1", "Apple", "Fruit", 1.00);
        //assert
        assertEquals("Apple", apple.getName());
    }

    @Test
    void getCategory() {
        //setup
        //execute
        Product apple = new Product("p1", "Apple", "Fruit", 1.00);
        //assert
        assertEquals("Fruit", apple.getCategory());
    }

    @Test
    void getPrice() {
        //setup
        //execute
        Product apple = new Product("p1", "Apple", "Fruit", 1.00);
        //assert
        assertEquals(1.00, apple.getPrice());
    }

    @ParameterizedTest
    @NullSource
    void productName(String invalidName) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product("p1", invalidName, "Fruit", 1.00);
        });
    }

    @Property
    void withPrice(
            @ForAll @DoubleRange(min = 0.0, max = 10000.0) double originalPrice,
            @ForAll @DoubleRange(min = 0.0, max = 10000.0) double newPrice
    ) {
        //setup
        Product apple = new Product("p1", "Apple", "Fruit", originalPrice);
        //execute
        Product updated = apple.withPrice(newPrice);
        //assert
        assertEquals(newPrice, updated.getPrice(), 0.0001);
        assertEquals(originalPrice, apple.getPrice(), 0.0001);
    }


}