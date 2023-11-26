package fi.tuni.prog3.weatherapp.api.responseclasses.currentweather;

public class Sys {    
    public final String country;
    public final Long sunrise;
    public final Long sunset;
    
    public Sys(String country, Long sunrise, Long sunset) {
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }
}
