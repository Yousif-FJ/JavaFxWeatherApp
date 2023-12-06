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
        var temperature = new Label("0 °C");
        temperature.setFont(new Font(48));
        var temperatureAligner = new HBox(temperature);
        temperatureAligner.setAlignment(Pos.CENTER);
        var feelsLike = new Label("Feels like -36 °C");
        feelsLike.setFont(new Font(smallFontSize));

        var tempsBox = new VBox();
        tempsBox.getChildren().addAll(
                temperatureAligner,
                feelsLike
                );
        tempsBox.setAlignment(Pos.CENTER);

        String iconPath = "/weathericons/03d@2x.png";
        var iconImage = new Image(getClass().getResource(iconPath).toExternalForm());
        var iconImageView = new ImageView(iconImage);
        iconImageView.setFitWidth(150);
        iconImageView.setFitHeight(150);
        var iconAligner = new VBox(iconImageView);
        iconAligner.setAlignment(Pos.CENTER);

        var humidity = new Label("Humidity: 95%");
        humidity.setFont(new Font(smallFontSize));
        var windSpeed = new Label("Wind speed: 4.02 m/s");
        windSpeed.setFont(new Font(smallFontSize));
        var sunrise = new Label("Sunrise: 9:06");
        sunrise.setFont(new Font(smallFontSize));
        var sunset = new Label("Sunset: 15:03");
        sunset.setFont(new Font(smallFontSize));

        var additionalDataBox = new VBox();
        additionalDataBox.getChildren().addAll(
                humidity,
                windSpeed,
                sunrise,
                sunset
                );
        additionalDataBox.setAlignment(Pos.CENTER_LEFT);

        var currentWeatherBox = new HBox();
        currentWeatherBox.setStyle("-fx-background-color: #ffffff;");

        currentWeatherBox.setPadding(new Insets(10));
        currentWeatherBox.getChildren().addAll(
                tempsBox,
                iconAligner,
                additionalDataBox
                );
        currentWeatherBox.setPrefHeight(168);

        return currentWeatherBox;
    }
}
