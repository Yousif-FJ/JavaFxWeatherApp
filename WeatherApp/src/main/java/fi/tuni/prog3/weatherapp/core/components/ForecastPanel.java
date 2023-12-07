package fi.tuni.prog3.weatherapp.core.components;

import fi.tuni.prog3.weatherapp.api.iAPI;
import fi.tuni.prog3.weatherapp.core.viewmodels.ForecastVm;
import fi.tuni.prog3.weatherapp.core.viewmodels.GlobalVm;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ForecastPanel {
    private final ForecastVm forecastVm = new ForecastVm();
    private final iAPI apiService;
    private final GlobalVm globalVm;

    public ForecastPanel(iAPI api, GlobalVm globalVm) {
        apiService = api;
        this.globalVm = globalVm;
    }


    public HBox create() {
        var forecastBox = new HBox();
        forecastBox.setStyle("-fx-background-color: #7AEAF1;");
        forecastBox.setPrefHeight(168);

        //LocalDate date = LocalDate.now(); 
        for (int i = 0; i < 5; i++) {
            // var weekdayShorthand =  date.getDayOfWeek().toString().substring(0, 3);
            // System.out.print(weekdayShorthand + "\n");
            // date = date.plusDays(1);

            var forecastElement = createForecastElement();


            forecastBox.getChildren().add(forecastElement);
        }

        return forecastBox;
    }

    private VBox createForecastElement(){
        var mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);
        mainVBox.setPadding(new Insets(0, 18, 0, 18));

        var dayLabel = new Label("Today");

        String iconPath = "/weathericons/01d@2x.png";
        var iconImage = new Image(getClass().getResource(iconPath).toExternalForm());
        var iconImageView = new ImageView(iconImage);

        var maxMinTempLabel = new Label("↑-2  ↓-3");
        maxMinTempLabel.setFont(new Font(24));

        mainVBox.getChildren().addAll(dayLabel, iconImageView, maxMinTempLabel);
        return mainVBox;
    }

    private void updateForecast(){
        var lat = globalVm.currentLocationItem.getValue().lat;
        var lon = globalVm.currentLocationItem.getValue().lon;
        var result = apiService.getForecast(lat, lon);
    }
}
