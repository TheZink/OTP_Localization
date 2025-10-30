package com.example;

import java.util.Locale;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppGUI extends Application{

    private ResourceBundle rb;
    private Language language = new Language();
    private Calculate calc = new Calculate();
    ShoppingCart cart = new ShoppingCart();

    private int itemCount = 0;
    private double totalAmount = 0.0;

    @Override
    public void start(Stage stage){
        setLocale("en", "US"); //Default language and country

        ComboBox<String> langComboBox = new ComboBox<>();
        langComboBox.getItems().setAll("English", "Finnish", "Swedish", "Japanese");
        langComboBox.getSelectionModel().selectFirst();

        TextField priceField = new TextField();
        priceField.setPromptText(rb.getString("prompt.price"));
        TextField quantityField = new TextField();
        quantityField.setPromptText(rb.getString("prompt.quantity"));

        Button addButton = new Button(rb.getString("prompt.add"));

        Label countLabel = new Label(rb.getString("items.count") + ": " + itemCount);
        Label totalLabel = new Label(rb.getString("total") + ": " + totalAmount + " euro");

        langComboBox.setOnAction(event -> {
            String selectLanguage = langComboBox.getSelectionModel().getSelectedItem();
            
            switch (selectLanguage){
                case "English": setLocale("en", "US"); break;
                case "Finnish": setLocale("fi", "FI"); break;
                case "Swedish": setLocale("sv", "SE"); break;
                case "Japanese": setLocale("ja", "JP"); break;
            }

            // Refresh texts
            priceField.setPromptText(rb.getString("prompt.price"));
            quantityField.setPromptText(rb.getString("prompt.quantity"));
            addButton.setText(rb.getString("prompt.add"));
            totalLabel.setText(rb.getString("total") + ": " + String.format("%.2f", totalAmount) + " euro");
            countLabel.setText(rb.getString("items.count") + ": " + itemCount);
        });

        Label langLabel = new Label("Language:");
        HBox langRow = new HBox(8, langLabel, langComboBox);

        addButton.setOnAction(event -> {
            try {
                double price = Double.parseDouble(priceField.getText().trim());
                int quantity = Integer.parseInt(quantityField.getText().trim());

                cart.setPrice((int) price);
                cart.setQuantity(quantity);

                double lineTotal = calc.calculate(cart);
                itemCount += quantity;
                totalAmount += lineTotal;

                countLabel.setText(rb.getString("items.count") + ": " + itemCount);
                totalLabel.setText(rb.getString("total") + ": " + String.format(Locale.getDefault(), "%.2f", totalAmount) + " euro");

                priceField.clear();
                quantityField.clear();

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Enter valid numbers");
                alert.showAndWait();
            }
        });

        GridPane inputGrid = new GridPane();
        inputGrid.setHgap(8);
        inputGrid.setVgap(8);
        inputGrid.add(priceField, 0, 0);
        inputGrid.add(quantityField, 1, 0);
        inputGrid.add(addButton, 2, 0);

        VBox root = new VBox(12, langRow, inputGrid, countLabel, totalLabel);
        root.setPadding(new Insets(12));

        Scene scene = new Scene(root, 420, 200);
        stage.setTitle("Shopping Cart (Ilkka Sinkonen)");
        stage.setScene(scene);
        stage.show();
    }
    
    private void setLocale(String lang, String country){
        language.setLanguage(lang);
        language.setCountry(country);

        Locale locale = new Locale(language.getLanguage(), language.getCountry());
        rb = ResourceBundle.getBundle("MessageBundle", locale);
    }
}
