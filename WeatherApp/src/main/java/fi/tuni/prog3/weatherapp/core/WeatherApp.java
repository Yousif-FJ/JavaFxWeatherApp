package fi.tuni.prog3.weatherapp.core;

import fi.tuni.prog3.weatherapp.api.UnitType;
import fi.tuni.prog3.weatherapp.api.WeatherApi;
import fi.tuni.prog3.weatherapp.api.iAPI;
import fi.tuni.prog3.weatherapp.core.ViewModels.CurrentWeatherVM;
import fi.tuni.prog3.weatherapp.core.ViewModels.SearchViewModel;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class WeatherApp extends Application {

    private final CurrentWeatherVM currentWeatherVm = new CurrentWeatherVM();
    private final SearchViewModel searchViewModel = new SearchViewModel();
    private final iAPI apiService = new WeatherApi(UnitType.Metric);
    private final SidePanel sidePanel = new SidePanel(searchViewModel, apiService);
    private final MainPanel mainPanel = new MainPanel();

    @Override
    public void start(Stage stage) {
    
        BorderPane root = new BorderPane();
        
        root.setCenter(createContent());
        
        var quitButton = createQuitButton();

        var hBox = new HBox(quitButton);
        hBox.spacingProperty().set(4);
        BorderPane.setMargin(hBox, new Insets(5, 10, 5, 10));
        root.setBottom(hBox);
        
        Scene scene = new Scene(root, 900, 582);                      
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
    
    private HBox createContent() {
        HBox hBox = new HBox();
        
        //Adding two VBox to the HBox.
        hBox.getChildren().addAll(sidePanel.createSidePanel(), mainPanel.createMainPanel());
        
        return hBox;
    }
    
    


    private Button createQuitButton() {
        Button button = new Button("Quit");
        
        //Adding an event to the button to terminate the application.
        button.setOnAction((ActionEvent event) -> {
            Platform.exit();
        });
        
        return button;
    }
}