package fi.tuni.prog3.weatherapp.core.ViewModels;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;


public class SearchViewModel {
    public final SimpleStringProperty searchValue = new SimpleStringProperty("");
    public final ObjectProperty<ObservableList<String>> searchResults = 
        new SimpleObjectProperty<ObservableList<String>>();
}
