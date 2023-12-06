package fi.tuni.prog3.weatherapp.core;

import java.util.stream.Collectors;
import fi.tuni.prog3.weatherapp.api.iAPI;
import fi.tuni.prog3.weatherapp.core.ViewModels.SearchViewModel;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SidePanel {
    private final SearchViewModel searchViewModel;
    private final iAPI apiService;

    public SidePanel(SearchViewModel searchViewModel, iAPI apiService) {
        this.searchViewModel = searchViewModel;
        this.apiService = apiService;
    }

    public VBox createSidePanel() {
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
        listView.itemsProperty().bind(searchViewModel.searchResultsDisplay);
        //This happens when user selects an item
        listView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                OnSelectedResult();
            }
        });
        //This happens when the selected item has changed. 
        //This event triggers with the previous one but additionally if the user changes the search 
        listView.getSelectionModel().selectedIndexProperty()
                                    .addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() != -1) {
                searchViewModel.selectedResultItem = searchViewModel.searchResultValue.get(newValue.intValue());
            }
        });

        searchButton.setOnAction(event -> OnSearch());

        HBox searchBox = new HBox(searchField, searchButton);
        leftHBox.getChildren().addAll(searchBox, listView);
        
        return leftHBox;
    }

    private void OnSelectedResult() {
        System.out.println(searchViewModel.selectedResultItem.lat);
        System.out.println(searchViewModel.selectedResultItem.lon);
        //Set the current temp
    }

    private void OnSearch() {
        String query = searchViewModel.searchValue.getValue();
        if (query != null && query.isEmpty() == false) {

            var resultLoc = apiService.lookUpLocation(query);
            if (resultLoc.isSuccess()) {
                
                var searchResults = resultLoc.getValue().stream()
                .map(x -> x.name + ", " + x.country)
                .collect(Collectors.toList());
                
                var observableListView = FXCollections.observableList(searchResults);
                searchViewModel.searchResultValue = resultLoc.getValue();
                searchViewModel.searchResultsDisplay.set(observableListView);
            }
        }
    }

}
