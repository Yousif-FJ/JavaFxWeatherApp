package fi.tuni.prog3.weatherapp.core;

import fi.tuni.prog3.weatherapp.api.UnitType;
import fi.tuni.prog3.weatherapp.api.WeatherApi;
import fi.tuni.prog3.weatherapp.api.iAPI;
import fi.tuni.prog3.weatherapp.core.components.CurrentWeatherPanel;
import fi.tuni.prog3.weatherapp.core.components.ForecastPanel;
import fi.tuni.prog3.weatherapp.core.components.LocationPanel;
import fi.tuni.prog3.weatherapp.core.viewmodels.CurrentWeatherVm;
import fi.tuni.prog3.weatherapp.core.viewmodels.GlobalVm;
import fi.tuni.prog3.weatherapp.core.viewmodels.SearchVm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class WeatherApp extends Application {

    private final CurrentWeatherVm currentWeatherVm = new CurrentWeatherVm();
    private final SearchVm searchViewModel = new SearchVm();
    private final GlobalVm globalVm = new GlobalVm();
    private final iAPI apiService = new WeatherApi(UnitType.Metric);
    private final LocationPanel sidePanel = new LocationPanel(searchViewModel, globalVm, apiService);
    private final CurrentWeatherPanel currentWeatherPanel = new CurrentWeatherPanel(currentWeatherVm, globalVm, apiService);
    private final ForecastPanel forecastPanel = new ForecastPanel(apiService, globalVm);

    @Override
    public void start(Stage stage) {
    
        BorderPane root = new BorderPane();
        
        root.setCenter(createContent());
                
        Scene scene = new Scene(root, 900, 504);                      
        stage.setScene(scene);
        stage.setTitle("WeatherApp");
        stage.show();


        //This is just a test for getting the weather in Tampere City
        TestWeatherApi();
    }

    private void TestWeatherApi() {
        var resultLoc = apiService.lookUpLocation("Tampere");
        if (resultLoc.isSuccess() == false) {
            return;
        }

        var lat = resultLoc.getValue().get(0).lat;
        var lon = resultLoc.getValue().get(0).lon;

        var resultCurrentWeather = apiService.getCurrentWeather(lat, lon);
        if (resultCurrentWeather.isSuccess() == false) {
            return;
        }
        System.out.println(resultCurrentWeather.getValue());

        var resultForecastWeather = apiService.getForecast(lat, lon);
        if (resultForecastWeather.isSuccess() == false) {
            return;
        }
        System.out.println(resultForecastWeather.getValue());
    }

    public static void main(String[] args) {
        launch();
    }
    
    private VBox createMainPanel() {
        VBox mainPanel = new VBox();
        mainPanel.setPrefWidth(700);
        mainPanel.setStyle("-fx-background-color: #b1c2d4;");
        mainPanel.getChildren().addAll(currentWeatherPanel.create(), forecastPanel.create());
        return mainPanel;
    }

    private HBox createContent() {
        HBox hBox = new HBox();
        
        //Adding two VBox to the HBox.
        hBox.getChildren().addAll(sidePanel.create(), createMainPanel());
        
        return hBox;
    }
}