package com.example;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Language language = new Language();
        ShoppingCart cart = new ShoppingCart();
        Calculate calc = new Calculate();
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select the language:");
        System.out.println("1. English");
        System.out.println("2. Finnish");
        System.out.println("3. Swedish");
        System.out.println("4. Japanese");
        
        int choice = scanner.nextInt();
        double total = 0;

        switch (choice) {
            case 1:
                language.setLanguage("en");
                language.setCountry("US");
                break;
            
            case 2:
                language.setLanguage("fi");
                language.setCountry("FI");
                break;
            
            case 3:
                language.setLanguage("sv");
                language.setCountry("SE");
                break;
            
            case 4:
                language.setLanguage("ja");
                language.setCountry("JP");
                break;
        }

        Locale locale = new Locale(language.getLanguage(), language.getCountry());
        ResourceBundle rb = ResourceBundle.getBundle("MessageBundle", locale);
        
        System.out.println(rb.getString("prompt.items"));
        int items = scanner.nextInt();

        for (int i = 0; i < items; i++){
            System.out.println((i+1) + " " + rb.getString("prompt.price"));
            int price = scanner.nextInt();

            System.out.println((i + 1) + " " + rb.getString("prompt.quantity"));
            int quantity = scanner.nextInt();

            cart.setPrice(price);
            cart.setQuantity(quantity);

            total += calc.calculate(cart);
        }
        
        System.out.println(rb.getString("total") + " " + total + " euro");        
    }
}