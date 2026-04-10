package org.example;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void getId() {
        //setup
        //execute
        Customer c = new Customer("c1", "Hunter", "70802");
        //assert
        assertEquals("c1", c.getId());
    }

    @Test
    void getName() {
        //setup
        //execute
        Customer c = new Customer("c1", "Hunter", "70802");
        //assert
        assertEquals("Hunter", c.getName());
    }

    @Test
    void getZipCode() {
        //setup
        //execute
        Customer c = new Customer("c1", "Hunter", "70802");
        //assert
        assertEquals("70802", c.getZipCode());
    }

    @Test
    void getLoyaltyPoints() {
        //setup
        Customer c = new Customer("c1", "Hunter", "70802");
        //execute
        c.addLoyaltyPoints(100);
        //assert
        assertEquals(100, c.getLoyaltyPoints());
    }

    @Property
    void addLoyaltyPoints(
            @ForAll @IntRange(min = 0, max = 5000) int first,
            @ForAll @IntRange(min = 0, max = 5000) int second
    ) {
        //setup
        Customer c = new Customer("c1", "Hunter", "70802");
        //execute
        c.addLoyaltyPoints(first);
        int afterFirst = c.getLoyaltyPoints();
        c.addLoyaltyPoints(second);
        int afterSecond = c.getLoyaltyPoints();
        //assert
        assertEquals(first, afterFirst);
        assertEquals(first + second, afterSecond);
        assertTrue(afterSecond >= afterFirst);
    }

    @ParameterizedTest
    @CsvSource({
            "0,     0.00",
            "49,    0.00",
            "50,    0.05",
            "200,   0.10",
            "500,   0.15",
    })
    void testDiscountRate(int loyaltyPoints, double expectedRate) {
        //setup
        Customer c = new Customer("c1", "Hunter", "70802");
        c.addLoyaltyPoints(loyaltyPoints);
        //execute
        double actualRate = c.getDiscountRate();
        //assert
        assertEquals(expectedRate, actualRate, 0.0001);
    }
}