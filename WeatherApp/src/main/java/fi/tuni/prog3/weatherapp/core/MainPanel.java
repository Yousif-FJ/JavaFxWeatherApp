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
        VBox rightHBox = new VBox();
        rightHBox.setPrefWidth(700);
        rightHBox.setStyle("-fx-background-color: #b1c2d4;");

        var currentTemperature = new Label("0°C");
        currentTemperature.setFont(new Font(24));

        String iconPath = "/weathericons/01d@2x.png";
        Image iconImage = new Image(getClass().getResource(iconPath).toExternalForm());
        ImageView iconImageView = new ImageView(iconImage);

        var currentWeatherBox = new HBox();
        currentWeatherBox.setStyle("-fx-background-color: #ffffff;");

        currentWeatherBox.setPadding(new Insets(10));
        currentWeatherBox.getChildren().addAll(currentTemperature, iconImageView);
        currentWeatherBox.setPrefHeight(168);

        var forecastBox = new HBox();
        forecastBox.setStyle("-fx-background-color: #7AEAF1;");
        forecastBox.setPrefHeight(168);


        rightHBox.getChildren().addAll(currentWeatherBox, forecastBox);

        return rightHBox;
    }
}
