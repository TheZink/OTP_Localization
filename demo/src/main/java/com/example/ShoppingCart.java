package com.example;
public class ShoppingCart {
    private int price;
    private int quantity;

    public void setPrice(int price){
        this.price = price;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }
}
