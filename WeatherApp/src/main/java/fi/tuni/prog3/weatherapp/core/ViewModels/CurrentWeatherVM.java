package fi.tuni.prog3.weatherapp.core.viewmodels;

import javafx.beans.property.SimpleStringProperty;

public class CurrentWeatherVm {
    public final SimpleStringProperty currentTemperature = new SimpleStringProperty("");
    public final SimpleStringProperty humidity =  new SimpleStringProperty("");
    public final SimpleStringProperty windSpeed = new SimpleStringProperty("");
    public final SimpleStringProperty maxTemperature = new SimpleStringProperty("");
    public final SimpleStringProperty minTemperature = new SimpleStringProperty("");
}
