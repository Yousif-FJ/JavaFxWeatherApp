package fi.tuni.prog3.weatherapp.core;

import fi.tuni.prog3.weatherapp.api.UnitType;
import fi.tuni.prog3.weatherapp.api.WeatherApi;
import fi.tuni.prog3.weatherapp.api.iAPI;
import fi.tuni.prog3.weatherapp.core.components.CurrentWeatherPanel;
import fi.tuni.prog3.weatherapp.core.components.ForecastPanel;
import fi.tuni.prog3.weatherapp.core.components.HourlyForecastPanel;
import fi.tuni.prog3.weatherapp.core.components.LocationPanel;
import fi.tuni.prog3.weatherapp.core.viewmodels.GlobalVm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class WeatherApp extends Application {

    private final GlobalVm globalVm = new GlobalVm();
    
    private final iAPI apiService = new WeatherApi(UnitType.Metric);

    private final LocationPanel locationPanel = new LocationPanel(globalVm, apiService);
    private final CurrentWeatherPanel currentWeatherPanel = new CurrentWeatherPanel(globalVm, apiService);
    private final ForecastPanel forecastPanel = new ForecastPanel(apiService, globalVm);
    private final HourlyForecastPanel hourlyForecastPanel = new HourlyForecastPanel(apiService, globalVm);

    @Override
    public void start(Stage stage) {
    
        BorderPane root = new BorderPane();
        
        HBox mainContainer = new HBox();
        mainContainer.getChildren().addAll(locationPanel.create(), createMainPanel());
        root.setCenter(mainContainer);
                
        Scene scene = new Scene(root, 900, 504);                      
        stage.setScene(scene);
        stage.setTitle("WeatherApp");
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
    
    private VBox createMainPanel() {
        VBox mainPanel = new VBox();
        mainPanel.setPrefWidth(700);
        mainPanel.setStyle("-fx-background-color: #b1c2d4;");
        mainPanel.getChildren().addAll(
                currentWeatherPanel.create(),
                forecastPanel.create(),
                hourlyForecastPanel.create());
        return mainPanel;
    }
}
