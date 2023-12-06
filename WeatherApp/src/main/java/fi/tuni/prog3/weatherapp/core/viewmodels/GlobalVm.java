package fi.tuni.prog3.weatherapp.core.viewmodels;

import fi.tuni.prog3.weatherapp.api.responseclasses.lookuplocation.LocationItemResponse;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class GlobalVm {
        public final ObjectProperty<LocationItemResponse> currentLocationItem =
            new SimpleObjectProperty<>();

}
