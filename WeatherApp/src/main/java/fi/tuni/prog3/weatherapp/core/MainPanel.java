package fi.tuni.prog3.weatherapp.core;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MainPanel {

    
    public VBox createMainPanel() {
        VBox rightHBox = new VBox();
        rightHBox.setPrefWidth(700);
        rightHBox.setStyle("-fx-background-color: #b1c2d4;");

        var currentTemperature = new Label("0");
        currentTemperature.setFont(new Font(24));
        var currentWeatherBox = new HBox();
        currentWeatherBox.setPadding(new Insets(10));
        currentWeatherBox.setPrefHeight(194);
        currentWeatherBox.getChildren().addAll(currentTemperature);

        var forecastBox = new HBox();
        currentWeatherBox.setPrefHeight(194);

        rightHBox.getChildren().addAll(currentWeatherBox, forecastBox);

        return rightHBox;
    }
}
