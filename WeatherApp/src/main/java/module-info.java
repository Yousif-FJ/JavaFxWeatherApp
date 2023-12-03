module fi.tuni.progthree.weatherapp {
    requires transitive javafx.controls;
    exports fi.tuni.prog3.weatherapp.core;
    exports fi.tuni.prog3.weatherapp.api.responseclasses.currentweather;
    exports fi.tuni.prog3.weatherapp.api.responseclasses.lookuplocation;
    exports fi.tuni.prog3.weatherapp.api.responseclasses.dailyforecast;
    exports fi.tuni.prog3.weatherapp.api.responseclasses.common;
    requires com.google.gson;
    requires java.net.http;
}
