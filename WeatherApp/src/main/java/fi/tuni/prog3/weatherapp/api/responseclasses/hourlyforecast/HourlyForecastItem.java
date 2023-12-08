package fi.tuni.prog3.weatherapp.api.responseclasses.hourlyforecast;

import java.util.List;

import fi.tuni.prog3.weatherapp.api.responseclasses.common.Clouds;
import fi.tuni.prog3.weatherapp.api.responseclasses.common.Main;
import fi.tuni.prog3.weatherapp.api.responseclasses.common.Weather;
import fi.tuni.prog3.weatherapp.api.responseclasses.common.Wind;

public class HourlyForecastItem {
    public final long dt;
    public final Main main;
    public final List<Weather> weather;
    public final Clouds cloud;
    public final Wind wind;
    public final int visibility;
    public final float pop;
    public final Sys sys;
    public final String dt_txt;
    
    public HourlyForecastItem(long dt, Main main, List<Weather> weather, Clouds cloud, Wind wind, int visibility,
            float pop, Sys sys, String dt_txt) {
        this.dt = dt;
        this.main = main;
        this.weather = weather;
        this.cloud = cloud;
        this.wind = wind;
        this.visibility = visibility;
        this.pop = pop;
        this.sys = sys;
        this.dt_txt = dt_txt;
    }

    @Override
    public String toString() {
        return "HourlyForecastItem [dt=" + dt + ", main=" + main + ", weather=" + weather + ", cloud=" + cloud
                + ", wind=" + wind + ", visibility=" + visibility + ", pop=" + pop + ", sys=" + sys + ", dt_txt="
                + dt_txt + "]";
    }
}
