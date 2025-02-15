package fi.tuni.prog3.weatherapp.core.viewmodels;

import java.util.List;

import fi.tuni.prog3.weatherapp.api.responseclasses.lookuplocation.LocationItemResponse;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;


public class SearchVm {
    public final SimpleStringProperty searchValue = new SimpleStringProperty("");
    public final ObjectProperty<ObservableList<String>> searchResultsDisplay = 
        new SimpleObjectProperty<ObservableList<String>>();

    public List<LocationItemResponse> searchResultValue;
}
