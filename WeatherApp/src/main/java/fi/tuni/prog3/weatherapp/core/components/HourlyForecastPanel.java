package fi.tuni.prog3.weatherapp.core.components;

import fi.tuni.prog3.weatherapp.api.iAPI;
import fi.tuni.prog3.weatherapp.core.utils.ImageUtil;
import fi.tuni.prog3.weatherapp.core.utils.TimeUtil;
import fi.tuni.prog3.weatherapp.core.viewmodels.ForecastHourVm;
import fi.tuni.prog3.weatherapp.core.viewmodels.ForecastVm;
import fi.tuni.prog3.weatherapp.core.viewmodels.GlobalVm;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class HourlyForecastPanel {
    private final ForecastVm forecastVm = new ForecastVm();
    private final iAPI apiService;
    private final GlobalVm globalVm;

    private final int forecastLength = 9;
    
    public HourlyForecastPanel(iAPI api, GlobalVm globalVm) {
        apiService = api;
        this.globalVm = globalVm;
    }

    public HBox create() {
        var hourlyForecastBox = new HBox();
        hourlyForecastBox.setStyle("-fx-background-color: #b1c2d4;");
        hourlyForecastBox.setPrefHeight(126);

        for (int i = 0; i < forecastLength; i++) {
            var forecastElement = createHourlyForecastElement();
            hourlyForecastBox.getChildren().add(forecastElement);
        }

        globalVm.currentLocationItem.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateHourlyForecast();
            }
        });

        return hourlyForecastBox;
    }

    private VBox createHourlyForecastElement(){

        var forecastHourVm = new ForecastHourVm();
        forecastVm.forecastHours.add(forecastHourVm);

        var mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setPadding(new Insets(0, 10, 0, 10));

        var hourLabel = new Label();
        hourLabel.textProperty().bind(forecastHourVm.hour);
        hourLabel.setFont(new Font(16));
        hourLabel.setStyle("-fx-font-weight: bold");

        var iconImageView = new ImageView();
        iconImageView.imageProperty().bind(forecastHourVm.iconImage);
        iconImageView.setFitWidth(50);
        iconImageView.setFitHeight(50);

        var temperatureLabel = new Label();
        temperatureLabel.textProperty().bind(forecastHourVm.temperature);
        temperatureLabel.setStyle("-fx-font-weight: bold");
        temperatureLabel.setFont(new Font(16));

        mainVBox.getChildren().addAll(hourLabel, iconImageView, temperatureLabel);
        return mainVBox;
    }

    private void updateHourlyForecast(){
        var lat = globalVm.currentLocationItem.getValue().lat;
        var lon = globalVm.currentLocationItem.getValue().lon;
        var result = apiService.getHourlyForecast(lat, lon);

        if (result.isSuccess() == false) {
            return;
        }

        var forecast = result.getValue();
        long timezone = forecast.city.timezone;
        for (int i = 0; i < forecastLength; i++) {
            var hourForecast = forecast.list.get(i);
            var hourForecastVm = forecastVm.forecastHours.get(i);

            var hour = TimeUtil.convertUnixTimestampToTime(hourForecast.dt, timezone);
            hourForecastVm.hour.setValue(hour);

            var temperature = String.valueOf(Math.round(hourForecast.main.temp)) + "°";
            hourForecastVm.temperature.setValue(temperature);

            var image = ImageUtil.createImage(this, hourForecast.weather.get(0).icon);
            hourForecastVm.iconImage.setValue(image);
        }
    }
}
