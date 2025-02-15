package fi.tuni.prog3.weatherapp.core.components;

import fi.tuni.prog3.weatherapp.api.iAPI;
import fi.tuni.prog3.weatherapp.core.utils.ImageUtil;
import fi.tuni.prog3.weatherapp.core.utils.TimeUtil;
import fi.tuni.prog3.weatherapp.core.viewmodels.CurrentWeatherVm;
import fi.tuni.prog3.weatherapp.core.viewmodels.GlobalVm;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CurrentWeatherPanel {
    private final CurrentWeatherVm currentWeatherVm = new CurrentWeatherVm();
    private final GlobalVm globalVm;
    private final iAPI apiService;

    private final double smallFontSize = 20;

    public CurrentWeatherPanel(GlobalVm globalVm, iAPI apiService) {
        this.apiService = apiService;
        this.globalVm = globalVm;
    }

    public HBox create() {
        var currentWeatherBox = new HBox(
                leftBox(),
                additionalDataBox());
        currentWeatherBox.setPrefHeight(189);
        currentWeatherBox.setStyle("-fx-background-color: #ffffff;");
        currentWeatherBox.setPadding(new Insets(10));

        globalVm.currentLocationItem.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateInformation();
            }
        });

        return currentWeatherBox;
    }

    private void updateInformation() {
        var lat = globalVm.currentLocationItem.getValue().lat;
        var lon = globalVm.currentLocationItem.getValue().lon;
        var result = apiService.getCurrentWeather(lat, lon);
        if (result.isSuccess() == false) {
            return;
        }
        var weather = result.getValue();
        currentWeatherVm.temperature.setValue(String.valueOf(Math.round(weather.main.temp)) + "°C");
        currentWeatherVm.feelsLike
                .setValue("Feels like " + String.valueOf(Math.round(weather.main.feels_like)) + "°");
        currentWeatherVm.minMaxTemperature.setValue(
                "↑" + String.valueOf(Math.round(weather.main.temp_max)) + "°  ↓"
                        + String.valueOf(Math.round(weather.main.temp_min)) + "°");
        currentWeatherVm.humidity.setValue("Humidity: " + String.valueOf(Math.round(weather.main.humidity)) + "%");
        currentWeatherVm.windSpeed.setValue("Wind speed: " + String.valueOf(weather.wind.speed) + " m/s");
        currentWeatherVm.sunrise.setValue(
                "Sunrise: " + String.valueOf(TimeUtil.convertUnixTimestampToTime(weather.sys.sunrise, weather.timezone)));
        currentWeatherVm.sunset.setValue(
                "Sunset: " + String.valueOf(TimeUtil.convertUnixTimestampToTime(weather.sys.sunset, weather.timezone)));
        
        var icon = ImageUtil.createImage(this, weather.weather.get(0).icon);
        currentWeatherVm.iconImage.setValue(icon);
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
        temperature.setStyle("-fx-font-weight: bold");
        var temperatureAligner = new HBox(temperature);
        temperatureAligner.setAlignment(Pos.CENTER);
        var feelsLike = new Label();
        feelsLike.textProperty().bind(currentWeatherVm.feelsLike);
        feelsLike.setFont(new Font(smallFontSize));
        var minMaxTemperature = new Label();
        minMaxTemperature.textProperty().bind(currentWeatherVm.minMaxTemperature);
        minMaxTemperature.setFont(new Font(smallFontSize + 2));
        minMaxTemperature.setStyle("-fx-font-weight: bold");

        var tempsBox = new VBox(
                temperatureAligner,
                feelsLike,
                minMaxTemperature);
        tempsBox.setAlignment(Pos.CENTER);
        return tempsBox;
    }

    private VBox iconBox() {
        var iconImageView = new ImageView();
        iconImageView.imageProperty().bind(currentWeatherVm.iconImage);
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
        var sunrise = new Label();
        sunrise.textProperty().bind(currentWeatherVm.sunrise);
        sunrise.setFont(new Font(smallFontSize));
        var sunset = new Label();
        sunset.textProperty().bind(currentWeatherVm.sunset);
        sunset.setFont(new Font(smallFontSize));

        var additionalDataBox = new VBox(
                humidity,
                windSpeed,
                sunrise,
                sunset);
        additionalDataBox.setAlignment(Pos.CENTER_LEFT);
        return additionalDataBox;
    }
}
