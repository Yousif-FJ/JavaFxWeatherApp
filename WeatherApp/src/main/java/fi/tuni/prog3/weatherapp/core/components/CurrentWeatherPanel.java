package fi.tuni.prog3.weatherapp.core.components;

import fi.tuni.prog3.weatherapp.api.iAPI;
import fi.tuni.prog3.weatherapp.core.viewmodels.CurrentWeatherVm;
import fi.tuni.prog3.weatherapp.core.viewmodels.GlobalVm;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class CurrentWeatherPanel {
    private final CurrentWeatherVm currentWeatherVm;
    private final GlobalVm globalVm;
    private final iAPI apiService;


    public CurrentWeatherPanel(CurrentWeatherVm currentWeatherVm, GlobalVm globalVm, iAPI apiService) {
        this.currentWeatherVm = currentWeatherVm;
        this.apiService = apiService;
        this.globalVm = globalVm;
    }


    public HBox create() {
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

        globalVm.currentLocationItem.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateCurrentWeather();
            }
        });

        return currentWeatherBox;
    }
    
    public void updateCurrentWeather(){
        var lat = globalVm.currentLocationItem.getValue().lat;
        var lon = globalVm.currentLocationItem.getValue().lon;
        var result = apiService.getCurrentWeather(lat, lon);
        if (result.isSuccess() == false) {
            return;
        }
        var weather = result.getValue();
        currentWeatherVm.currentTemperature.setValue(String.valueOf(weather.main.temp));
        currentWeatherVm.maxTemperature.setValue(String.valueOf(weather.main.temp_max));
        currentWeatherVm.minTemperature.setValue(String.valueOf(weather.main.temp_min));
        currentWeatherVm.humidity.setValue(String.valueOf(weather.main.humidity));
        currentWeatherVm.windSpeed.setValue(String.valueOf(weather.wind.speed));
    }
}
