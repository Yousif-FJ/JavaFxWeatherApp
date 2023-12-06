package fi.tuni.prog3.weatherapp.core.viewmodels;

import javafx.beans.property.SimpleStringProperty;

public class ForecastDayVm {
    public final SimpleStringProperty dayName = new SimpleStringProperty("");
    public final SimpleStringProperty maxTemp = new SimpleStringProperty("");
    public final SimpleStringProperty minTemp = new SimpleStringProperty("");
}
