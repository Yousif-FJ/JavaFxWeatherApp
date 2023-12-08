package fi.tuni.prog3.weatherapp.api.responseclasses.hourlyforecast;

public class City {
    public final long timezone;
    public final long sunrise;
    public final long sunset;
    
    public City(long timezone, long sunrise, long sunset) {
        this.timezone = timezone;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        return "City [timezone=" + timezone + ", sunrise=" + sunrise + ", sunset=" + sunset + "]";
    }
}
