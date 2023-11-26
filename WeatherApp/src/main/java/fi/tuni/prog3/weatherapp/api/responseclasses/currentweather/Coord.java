package fi.tuni.prog3.weatherapp.api.responseclasses.currentweather;

public class Coord {    
    public final String lon;
    public final String lat;
    
    public Coord(String lon, String lat) {
        this.lon = lon;
        this.lat = lat;
    }
}
