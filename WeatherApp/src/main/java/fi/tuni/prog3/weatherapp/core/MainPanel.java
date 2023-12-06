package fi.tuni.prog3.weatherapp.core;

import fi.tuni.prog3.weatherapp.api.iAPI;
import fi.tuni.prog3.weatherapp.core.ViewModels.CurrentWeatherVM;
import fi.tuni.prog3.weatherapp.core.ViewModels.GlobalVm;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MainPanel {
    private final CurrentWeatherVM currentWeatherVm;
    private final GlobalVm globalVm;
    private final iAPI apiService;


    public MainPanel(CurrentWeatherVM currentWeatherVm, GlobalVm globalVm, iAPI apiService) {
        this.currentWeatherVm = currentWeatherVm;
        this.apiService = apiService;
        this.globalVm = globalVm;
    }


    public VBox createMainPanel() {
        VBox mainPanel = new VBox();
        mainPanel.setPrefWidth(700);
        mainPanel.setStyle("-fx-background-color: #b1c2d4;");
        mainPanel.getChildren().addAll(createCurrentWeatherBox(), createForecastBox());
        return mainPanel;
    }

    private HBox createCurrentWeatherBox() {
        var temperature = new Label("0 °C");
        temperature.setFont(new Font(24));

        var humidity = new Label("Humidity: 95%");
        var windSpeed = new Label("Wind speed: 4.02 m/s");

        String iconPath = "/weathericons/01d@2x.png";
        var iconImage = new Image(getClass().getResource(iconPath).toExternalForm());
        var iconImageView = new ImageView(iconImage);

        var currentWeatherBox = new HBox();
        currentWeatherBox.setStyle("-fx-background-color: #ffffff;");

        currentWeatherBox.setPadding(new Insets(10));
        currentWeatherBox.getChildren().addAll(
                temperature,
                iconImageView,
                humidity,
                windSpeed
                );
        currentWeatherBox.setPrefHeight(168);

        return currentWeatherBox;
    }

    private HBox createForecastBox() {
        var forecastBox = new HBox();
        forecastBox.setStyle("-fx-background-color: #7AEAF1;");
        forecastBox.setPrefHeight(168);
        return forecastBox;
    }
}
