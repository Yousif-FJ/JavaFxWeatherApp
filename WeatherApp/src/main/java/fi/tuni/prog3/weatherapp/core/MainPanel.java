package fi.tuni.prog3.weatherapp.core;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MainPanel {

    
    public VBox createMainPanel() {
        VBox rightHBox = new VBox();
        rightHBox.setPrefWidth(700);
        rightHBox.setStyle("-fx-background-color: #b1c2d4;");

        var currentTemperature = new Label("0°C");
        currentTemperature.setFont(new Font(24));

        String iconPath = "/weathericons/01d@2x.png";
        Image iconImage = new Image(getClass().getResource(iconPath).toExternalForm());
        ImageView iconImageView = new ImageView(iconImage);

        var currentWeatherBox = new HBox();
        currentWeatherBox.setPadding(new Insets(10));
        currentWeatherBox.setPrefHeight(194);
        currentWeatherBox.getChildren().addAll(currentTemperature, iconImageView);

        var forecastBox = new HBox();
        currentWeatherBox.setPrefHeight(194);

        rightHBox.getChildren().addAll(currentWeatherBox, forecastBox);

        return rightHBox;
    }
}
