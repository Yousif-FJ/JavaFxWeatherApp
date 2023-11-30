package fi.tuni.prog3.weatherapp.api.responseclasses.currentweather;

import java.util.List;

public class CurrentWeatherResponse {
    
    public final Coord coord;
    public final List<Weather> weather;
    public final String base;
    public final Main main;
    public final long visibility;
    public final Wind wind;
    public final Rain rain;
    public final long dt;
    public final Sys sys;
    public final long timezone;
    public final String name;
    
    public CurrentWeatherResponse(Coord coord, List<Weather> weather, String base, Main main, long visibility, Wind wind, Rain rain,
            long dt, Sys sys, long timezone, String name) {
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.rain = rain;
        this.dt = dt;
        this.sys = sys;
        this.timezone = timezone;
        this.name = name;
    }

    @Override
    public String toString() {
        return "CurrentWeatherResponse [coord=" + coord + ", weather=" + weather + ", base=" + base + ", main=" + main
                + ", visibility=" + visibility + ", wind=" + wind + ", rain=" + rain + ", dt=" + dt + ", sys=" + sys
                + ", timezone=" + timezone + ", name=" + name + "]";
    }
}
