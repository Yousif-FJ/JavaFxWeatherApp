package fi.tuni.prog3.weatherapp.core.viewmodels;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

public class CurrentWeatherVm {
    public final SimpleStringProperty temperature = new SimpleStringProperty("");
    public final SimpleStringProperty feelsLike = new SimpleStringProperty("");
    public final SimpleStringProperty humidity =  new SimpleStringProperty("");
    public final SimpleStringProperty windSpeed = new SimpleStringProperty("");
    public final SimpleStringProperty minMaxTemperature = new SimpleStringProperty("");
    public final SimpleStringProperty sunrise = new SimpleStringProperty("");
    public final SimpleStringProperty sunset = new SimpleStringProperty("");
    public final ObjectProperty<Image> iconImage = new SimpleObjectProperty<>();
}
