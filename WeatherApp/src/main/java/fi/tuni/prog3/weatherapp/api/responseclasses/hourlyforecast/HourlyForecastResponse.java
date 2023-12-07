package fi.tuni.prog3.weatherapp.api.responseclasses.hourlyforecast;

import java.util.List;

public class HourlyForecastResponse {
    public final int cnt;
    public final List<HourlyForecastItem> list;
    public final long timezone;
    public final long sunrise;
    public final long sunset;
    
    public HourlyForecastResponse(int cnt, List<HourlyForecastItem> list, long timezone, long sunrise, long sunset) {
        this.cnt = cnt;
        this.list = list;
        this.timezone = timezone;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }
}
