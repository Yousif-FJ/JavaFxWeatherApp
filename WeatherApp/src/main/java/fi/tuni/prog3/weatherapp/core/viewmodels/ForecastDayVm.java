package fi.tuni.prog3.weatherapp.core.viewmodels;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;

public class ForecastDayVm {
    public final SimpleStringProperty dayName = new SimpleStringProperty("");
    public final SimpleStringProperty minMaxTempString = new SimpleStringProperty("↑-  ↓-");
    public final ObjectProperty<Image> iconImage = new SimpleObjectProperty<>();
}
