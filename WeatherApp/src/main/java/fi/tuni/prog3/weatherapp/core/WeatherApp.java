package fi.tuni.prog3.weatherapp.core;

import java.util.stream.Collectors;

import fi.tuni.prog3.weatherapp.api.UnitType;
import fi.tuni.prog3.weatherapp.api.WeatherApi;
import fi.tuni.prog3.weatherapp.api.iAPI;
import fi.tuni.prog3.weatherapp.core.ViewModels.CurrentWeatherVM;
import fi.tuni.prog3.weatherapp.core.ViewModels.SearchViewModel;
import javafx.collections.FXCollections;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class WeatherApp extends Application {

    private final CurrentWeatherVM currentWeatherVm = new CurrentWeatherVM();
    private final SearchViewModel searchViewModel = new SearchViewModel();
    private final iAPI apiService = new WeatherApi(UnitType.Metric);


    @Override
    public void start(Stage stage) {
    
        BorderPane root = new BorderPane();
        
        root.setCenter(createContent());
        
        var quitButton = createQuitButton();

        var hBox = new HBox(quitButton);
        hBox.spacingProperty().set(4);
        BorderPane.setMargin(hBox, new Insets(5, 10, 5, 10));
        root.setBottom(hBox);
        
        Scene scene = new Scene(root, 900, 560);                      
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
        //Creating an HBox.
        HBox hBox = new HBox();
        
        //Adding two VBox to the HBox.
        hBox.getChildren().addAll(createSidePanel(), createMainPanel());
        
        return hBox;
    }
    
    private VBox createSidePanel() {
        //Creating a VBox for the left side.
        VBox leftHBox = new VBox();
        leftHBox.setPrefWidth(200);
        leftHBox.setStyle("-fx-background-color: #D9D9D9;");
    
        Button searchButton = new Button("Search");
        TextField searchField = new TextField();
        searchField.textProperty().bindBidirectional(searchViewModel.searchValue);
        searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchButton.fire();     
            }
        });
        
        ListView<String> listView = new ListView<>();
        listView.itemsProperty().bind(searchViewModel.searchResults);

        searchButton.setOnAction(OnSearch());

        HBox searchBox = new HBox(searchField, searchButton);
        leftHBox.getChildren().addAll(searchBox, listView);
        
        return leftHBox;
    }

    private EventHandler<ActionEvent> OnSearch() {
        return event -> {
            String query = searchViewModel.searchValue.getValue();
            if (query != null && query.isEmpty() == false) {

                var resultLoc = apiService.lookUpLocation(query);
                if (resultLoc.isSuccess()) {

                    var searchResults = resultLoc.getValue().stream()
                                                .map(x -> x.name + ", " +  x.country)
                                                .collect(Collectors.toList());

                    var observableListView = FXCollections.observableList(searchResults);
                    searchViewModel.searchResults.set(observableListView);
                }
            }
        };
    }
    
    private VBox createMainPanel() {
        //Creating a VBox for the right side.
        VBox rightHBox = new VBox();
        rightHBox.setPrefWidth(700);
        rightHBox.setStyle("-fx-background-color: #b1c2d4;");
        
        var mainLabel = new Label("Main panel");
        rightHBox.getChildren().add(mainLabel);
        
        return rightHBox;
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