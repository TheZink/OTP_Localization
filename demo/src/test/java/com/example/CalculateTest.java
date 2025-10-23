package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculateTest {

    @Test
    void testCalculate() {
        ShoppingCart cart = new ShoppingCart();
        Calculate calc = new Calculate();

        cart.setPrice(3);
        cart.setQuantity(2);

        assertEquals(6, calc.calculate(cart));
    }
}
