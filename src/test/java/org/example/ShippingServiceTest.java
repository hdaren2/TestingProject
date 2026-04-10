package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShippingServiceTest {

    @Test
    void estimateShipping() {
        //setup
        ShippingService ss = new ShippingService(0.20, 0.15);
        Customer c = new Customer("c1", "Hunter", "70802");
        //execute
        double shippingRate = ss.estimateShipping(c, 5);
        //assert
        assertEquals(0.95, shippingRate, 0.001);
    }

    @Test
    void negativeBaseRate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ShippingService(-0.20, 0.15);
        });
    }

    //Mock test
    @Test
    void zipCodeStartsWithNine() {
        //setup
        ShippingService ss = new ShippingService(0.20, 0.15);
        Customer mockCustomer = mock(Customer.class);
        when(mockCustomer.getZipCode()).thenReturn("90210");
        //execute
        double shippingRate = ss.estimateShipping(mockCustomer, 5);
        // assert
        assertEquals(1.14, shippingRate, 0.001);
        verify(mockCustomer).getZipCode();
    }

}