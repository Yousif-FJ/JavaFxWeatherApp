package fi.tuni.prog3.weatherapp.core.viewmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class ForecastHourVm {
    public final SimpleStringProperty hour = new SimpleStringProperty("");
    public final SimpleStringProperty temperature = new SimpleStringProperty("");
    public final ObjectProperty<Image> iconImage = new SimpleObjectProperty<>();
}
