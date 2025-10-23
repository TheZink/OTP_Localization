package com.example;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ShoppingCart {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select the language:");
        System.out.println("1. English");
        System.out.println("2. Finnish");
        System.out.println("3. Swedish");
        System.out.println("4. Japanese");
        
        int choice = scanner.nextInt();

        String language = null;
        String country = null;

        switch (choice) {
            case 1:
                language = "en";
                country = "US";
                break;
            
            case 2:
                language = "fi";
                country = "FI";
                break;
            
            case 3:
                language = "sv";
                country = "SE";
                break;
            
            case 4:
                language = "ja";
                country = "JP";
                break;
        }

        Locale locale = new Locale(language, country);
        ResourceBundle rb = ResourceBundle.getBundle("MessageBundle", locale);

        System.out.println(rb.getString("prompt.items"));
        int items = scanner.nextInt();

        float total = 0;

        for (int i = 0; i < items; i++){
            System.out.println((i+1) + " " + rb.getString("prompt.price"));
            int price = scanner.nextInt();

            System.out.println((i + 1) + " " + rb.getString("prompt.quantity"));
            int quantity = scanner.nextInt();

            total += (price * quantity);
        }

        System.out.println(rb.getString("total") + " " + total + " euro");

        scanner.close();
    }

}
