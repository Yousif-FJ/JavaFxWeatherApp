package fi.tuni.prog3.weatherapp.core.components;

import fi.tuni.prog3.weatherapp.api.iAPI;
import fi.tuni.prog3.weatherapp.core.viewmodels.CurrentWeatherVm;
import fi.tuni.prog3.weatherapp.core.viewmodels.GlobalVm;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CurrentWeatherPanel {
    private final CurrentWeatherVm currentWeatherVm;
    private final GlobalVm globalVm;
    private final iAPI apiService;

    private final double smallFontSize = 20;

    public CurrentWeatherPanel(CurrentWeatherVm currentWeatherVm, GlobalVm globalVm, iAPI apiService) {
        this.currentWeatherVm = currentWeatherVm;
        this.apiService = apiService;
        this.globalVm = globalVm;
    }


    public HBox create() {
        var currentWeatherBox = new HBox(
                leftBox(),
                additionalDataBox()
                );
        currentWeatherBox.setPrefHeight(168);
        currentWeatherBox.setStyle("-fx-background-color: #ffffff;");
        currentWeatherBox.setPadding(new Insets(10));

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
        currentWeatherVm.temperature.setValue(String.valueOf(Math.round(weather.main.temp)) + " °C");
        currentWeatherVm.feelsLike.setValue("Feels like " + String.valueOf(Math.round(weather.main.feels_like)) + " °C");
        currentWeatherVm.maxTemperature.setValue(String.valueOf(weather.main.temp_max));
        currentWeatherVm.minTemperature.setValue(String.valueOf(weather.main.temp_min));
        currentWeatherVm.humidity.setValue("Humidity: " + String.valueOf(Math.round(weather.main.humidity)) + "%");
        currentWeatherVm.windSpeed.setValue("Wind speed: " + String.valueOf(weather.wind.speed) + " m/s");
    }

    private HBox leftBox() {
        var leftBox = new HBox(tempsBox(), iconBox());
        leftBox.setPadding(new Insets(0, 50, 0, 10));
        return leftBox;
    }

    private VBox tempsBox() {
        var temperature = new Label();
        temperature.textProperty().bind(currentWeatherVm.temperature);
        temperature.setFont(new Font(48));
        var temperatureAligner = new HBox(temperature);
        temperatureAligner.setAlignment(Pos.CENTER);
        var feelsLike = new Label();
        feelsLike.textProperty().bind(currentWeatherVm.feelsLike);
        feelsLike.setFont(new Font(smallFontSize));

        var tempsBox = new VBox(
                temperatureAligner,
                feelsLike
                );
        tempsBox.setAlignment(Pos.CENTER);
        return tempsBox;
    }

    private VBox iconBox() {
        String iconPath = "/weathericons/03d@2x.png";
        var iconImage = new Image(getClass().getResource(iconPath).toExternalForm());
        var iconImageView = new ImageView(iconImage);
        iconImageView.setFitWidth(150);
        iconImageView.setFitHeight(150);
        var iconAligner = new VBox(iconImageView);
        iconAligner.setAlignment(Pos.CENTER);
        return iconAligner;
    }

    private VBox additionalDataBox() {
        var humidity = new Label();
        humidity.textProperty().bind(currentWeatherVm.humidity);
        humidity.setFont(new Font(smallFontSize));
        var windSpeed = new Label();
        windSpeed.textProperty().bind(currentWeatherVm.windSpeed);
        windSpeed.setFont(new Font(smallFontSize));
        var sunrise = new Label("Sunrise: 9:06");
        sunrise.setFont(new Font(smallFontSize));
        var sunset = new Label("Sunset: 15:03");
        sunset.setFont(new Font(smallFontSize));

        var additionalDataBox = new VBox(
                humidity,
                windSpeed,
                sunrise,
                sunset
                );
        additionalDataBox.setAlignment(Pos.CENTER_LEFT);
        return additionalDataBox;
    }
}
