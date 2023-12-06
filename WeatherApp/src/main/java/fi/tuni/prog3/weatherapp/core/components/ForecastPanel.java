package fi.tuni.prog3.weatherapp.core.components;

import javafx.scene.layout.HBox;

public class ForecastPanel {

    public HBox create() {
        var forecastBox = new HBox();
        forecastBox.setStyle("-fx-background-color: #7AEAF1;");
        forecastBox.setPrefHeight(168);
        return forecastBox;
    }
}
