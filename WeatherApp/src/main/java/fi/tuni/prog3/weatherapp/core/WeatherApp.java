package fi.tuni.prog3.weatherapp.core;

import fi.tuni.prog3.weatherapp.api.UnitType;
import fi.tuni.prog3.weatherapp.api.WeatherApi;
import fi.tuni.prog3.weatherapp.api.iAPI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * JavaFX Sisu
 */
public class WeatherApp extends Application {

    private CurrentWeatherVM currentWeatherVm;
    @Override
    public void start(Stage stage) {
        
        currentWeatherVm = new CurrentWeatherVM();

        BorderPane root = new BorderPane();
        
        root.setCenter(createContent());
        
        var quitButton = createQuitButton();
        //I created this button to demonstrate binding in JavaFx
        //Check createMainPanel for more details 
        var anotherButton = createTestButton();
        var hBox = new HBox(quitButton, anotherButton);
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
        iAPI apiService = new WeatherApi(UnitType.Metric);
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
    
        TextField searchField = new TextField();
        Button searchButton = new Button("Search");
        ListView<String> listView = new ListView<>();

        searchButton.setOnAction(event -> {
            String query = searchField.getText();
            if (query != null && query.isEmpty() == false) {
                //TODO: Search for data sent put the result
                //listView.setItems();
                return;
            }});

        HBox searchBox = new HBox(searchField, searchButton);
        leftHBox.getChildren().addAll(searchBox, listView);
        
        return leftHBox;
    }
    
    private VBox createMainPanel() {
        //Creating a VBox for the right side.
        VBox rightHBox = new VBox();
        rightHBox.setPrefWidth(700);
        rightHBox.setStyle("-fx-background-color: #b1c2d4;");
        
        //Bind the label value to the viewModel value, 
        //so that the label automatically changes when we change the property
        var mainLabel = new Label();
        mainLabel.textProperty().bind(currentWeatherVm.Test);
        rightHBox.getChildren().add(mainLabel);
        
        return rightHBox;
    }

    private Button createTestButton() {
        Button button = new Button("Change label value");
        
        //Changing the property value will should change the Label value because a binding was created.
        button.setOnAction((ActionEvent event) -> {
            this.currentWeatherVm.Test.set("New Value");
        });
        
        return button;
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