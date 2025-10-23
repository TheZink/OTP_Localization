package com.example;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LecTest {
    Scanner scanner = new Scanner(System.in);
    String languange = scanner.nextLine();

    String country = scanner.nextLine();

    Locale locale = new Locale(languange, country);
    ResourceBundle rb = ResourceBundle.getBundle("MessageBundle", locale);
    
    String message = rb.getString("wish");
    String message2 = rb.getString("greeting");

    Locale myLocale = Locale.getDefault();
    
}