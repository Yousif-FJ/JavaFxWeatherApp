package fi.tuni.prog3.weatherapp.api;

public enum UnitType{
    Metric("metric"),
    Imperial("imperial");

    public final String value;
    private UnitType (String value){
        this.value = value;
    }
}