package com.example;

public class Calculate {

    public double calculate(ShoppingCart cart){
        return cart.getPrice() * cart.getQuantity();
    }
}
