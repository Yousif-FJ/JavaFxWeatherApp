package fi.tuni.prog3.weatherapp.core.components;

import java.util.stream.Collectors;
import fi.tuni.prog3.weatherapp.api.iAPI;
import fi.tuni.prog3.weatherapp.core.viewmodels.GlobalVm;
import fi.tuni.prog3.weatherapp.core.viewmodels.SearchVm;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LocationPanel {
    private final SearchVm searchViewModel = new SearchVm();
    private final GlobalVm globalVm;
    private final iAPI apiService;

    public LocationPanel(GlobalVm globalVm, iAPI apiService) {
        this.globalVm = globalVm;
        this.apiService = apiService;
    }

    public VBox create() {
        VBox leftHBox = new VBox();
        leftHBox.setPrefWidth(200);
        leftHBox.setStyle("-fx-background-color: #D9D9D9;");
    
        Button searchButton = new Button("Search");
        searchButton.setPrefWidth(100);
        searchButton.setOnAction(event -> OnSearch());

        TextField searchField = new TextField();
        searchField.textProperty().bindBidirectional(searchViewModel.searchValue);
        searchField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchButton.fire();     
            }
        });

        ListView<String> listView = new ListView<>();
        listView.itemsProperty().bind(searchViewModel.searchResultsDisplay);
        listView.setStyle("-fx-control-inner-background: #D9D9D9;");
        listView.getSelectionModel().selectedIndexProperty()
                                    .addListener((observable, oldValue, newValue) -> {
            OnSelectedIndexChanged(newValue);
        });


        HBox searchBox = new HBox(searchField, searchButton);
        leftHBox.getChildren().addAll(searchBox, listView);
        
        return leftHBox;
    }

    private void OnSelectedIndexChanged(Number newValue) {
        if (newValue.intValue() != -1) {
            var selectedItem = searchViewModel.searchResultValue.get(newValue.intValue());
            globalVm.currentLocationItem.setValue(selectedItem);
        }
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
