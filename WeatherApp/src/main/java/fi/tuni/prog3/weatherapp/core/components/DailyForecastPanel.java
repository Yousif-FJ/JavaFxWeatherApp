package fi.tuni.prog3.weatherapp.core.components;

import java.time.LocalDate;
import fi.tuni.prog3.weatherapp.api.iAPI;
import fi.tuni.prog3.weatherapp.core.utils.ImageUtil;
import fi.tuni.prog3.weatherapp.core.viewmodels.ForecastDayVm;
import fi.tuni.prog3.weatherapp.core.viewmodels.ForecastVm;
import fi.tuni.prog3.weatherapp.core.viewmodels.GlobalVm;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class DailyForecastPanel {
    private final ForecastVm forecastVm = new ForecastVm();
    private final iAPI apiService;
    private final GlobalVm globalVm;

    public DailyForecastPanel(iAPI api, GlobalVm globalVm) {
        apiService = api;
        this.globalVm = globalVm;
    }


    public HBox create() {
        var forecastBox = new HBox();
        forecastBox.setStyle("-fx-background-color: #7AEAF1;");
        forecastBox.setPrefHeight(189);

        for (int i = 0; i < 5; i++) {            
            forecastBox.getChildren().add(createForecastElement());
        }

        globalVm.currentLocationItem.addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateInformation();
            }
        });

        return forecastBox;
    }

    private VBox createForecastElement(){

        var forecastDayVm = new ForecastDayVm();
        forecastVm.forecastDays.add(forecastDayVm);

        var mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setPadding(new Insets(0, 10, 0, 10));

        var dayLabel = new Label();
        dayLabel.textProperty().bind(forecastDayVm.dayName);
        dayLabel.setFont(new Font(16));
        dayLabel.setStyle("-fx-font-weight: bold");


        var iconImageView = new ImageView();
        iconImageView.imageProperty().bind(forecastDayVm.iconImage);

        var maxMinTempLabel = new Label();
        maxMinTempLabel.textProperty().bind(forecastDayVm.minMaxTempString);
        maxMinTempLabel.setStyle("-fx-font-weight: bold");
        maxMinTempLabel.setFont(new Font(20));

        mainVBox.getChildren().addAll(dayLabel, iconImageView, maxMinTempLabel);
        return mainVBox;
    }

    private void updateInformation(){
        var lat = globalVm.currentLocationItem.getValue().lat;
        var lon = globalVm.currentLocationItem.getValue().lon;
        var result = apiService.getForecast(lat, lon);

        if (result.isSuccess() == false) {
            return;
        }

        LocalDate date = LocalDate.now(); 
        var forecast = result.getValue();
        for (int i = 0; i < 5; i++) {
            var dayForecast = forecast.list.get(i);
            var dayForecastVm = forecastVm.forecastDays.get(i);

            var maxTemp = Math.round(dayForecast.temp.max);
            var minTemp = Math.round(dayForecast.temp.min);

            String minMaxString = "↑" + maxTemp +"°  ↓"+ minTemp + "°";
            dayForecastVm.minMaxTempString.setValue(minMaxString);

            var weekdayShorthand =  date.getDayOfWeek().toString().substring(0, 3);
            weekdayShorthand =  ToNormalCase(weekdayShorthand);
            date = date.plusDays(1);
            dayForecastVm.dayName.setValue(weekdayShorthand);

            var image = ImageUtil.createImage(this, dayForecast.weather.get(0).icon);
            dayForecastVm.iconImage.setValue(image);
        }
    }

    private String ToNormalCase(String word){
        var lastPart = word.substring(1).toLowerCase();
        var firstLetter =  word.substring(0, 1).toUpperCase();

        return firstLetter + lastPart;
    }
}
