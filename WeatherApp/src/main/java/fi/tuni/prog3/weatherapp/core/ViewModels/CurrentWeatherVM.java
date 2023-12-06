package fi.tuni.prog3.weatherapp.core.ViewModels;

import javafx.beans.property.SimpleStringProperty;

public class CurrentWeatherVm {
    public final SimpleStringProperty currentTemperature = new SimpleStringProperty("");
    public final SimpleStringProperty humidity =  new SimpleStringProperty("");
    public final SimpleStringProperty windSpeed = new SimpleStringProperty("");
}
