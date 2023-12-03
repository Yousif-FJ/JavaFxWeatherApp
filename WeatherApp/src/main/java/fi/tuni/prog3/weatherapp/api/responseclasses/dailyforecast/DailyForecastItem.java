package fi.tuni.prog3.weatherapp.api.responseclasses.dailyforecast;

import java.util.List;
import fi.tuni.prog3.weatherapp.api.responseclasses.common.Weather;

public class DailyForecastItem {
    public final long dt;
    public final long sunrise;
    public final long sunset;
    public final Temp temp;
    public final FeelsLike feels_like;
    public final int pressure;
    public final int humidity;
    public final List<Weather> weather;
    public final float speed;
    public final float deg;
    public final float gust;
    public final int clouds;
    public final float pop;
    public final float rain;
    
    public DailyForecastItem(long dt, long sunrise, long sunset, Temp temp, FeelsLike feels_like, int pressure,
            int humidity, List<Weather> weather, float speed, float deg, float gust, int clouds, float pop,
            float rain) {
        this.dt = dt;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.temp = temp;
        this.feels_like = feels_like;
        this.pressure = pressure;
        this.humidity = humidity;
        this.weather = weather;
        this.speed = speed;
        this.deg = deg;
        this.gust = gust;
        this.clouds = clouds;
        this.pop = pop;
        this.rain = rain;
    }

    @Override
    public String toString() {
        return "DailyForecastItem [dt=" + dt + ", sunrise=" + sunrise + ", sunset=" + sunset + ", temp=" + temp
                + ", feels_like=" + feels_like + ", pressure=" + pressure + ", humidity=" + humidity + ", weather="
                + weather + ", speed=" + speed + ", deg=" + deg + ", gust=" + gust + ", clouds=" + clouds + ", pop="
                + pop + ", rain=" + rain + "]";
    }
}
