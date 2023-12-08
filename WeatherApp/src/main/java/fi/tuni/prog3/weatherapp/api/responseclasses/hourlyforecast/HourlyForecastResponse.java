package fi.tuni.prog3.weatherapp.api.responseclasses.hourlyforecast;

import java.util.List;

public class HourlyForecastResponse {
    public final int cnt;
    public final List<HourlyForecastItem> list;
    public final City city;
    
    public HourlyForecastResponse(int cnt, List<HourlyForecastItem> list, City city) {
        this.cnt = cnt;
        this.list = list;
        this.city = city;
    }

    @Override
    public String toString() {
        return "HourlyForecastResponse [cnt=" + cnt + ", list=" + list + ", city=" + city + "]";
    }
}
