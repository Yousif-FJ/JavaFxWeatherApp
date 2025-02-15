package fi.tuni.prog3.weatherapp.api;

import java.util.ArrayList;

import fi.tuni.prog3.weatherapp.api.responseclasses.currentweather.CurrentWeatherResponse;
import fi.tuni.prog3.weatherapp.api.responseclasses.dailyforecast.DailyForecastResponse;
import fi.tuni.prog3.weatherapp.api.responseclasses.hourlyforecast.HourlyForecastResponse;
import fi.tuni.prog3.weatherapp.api.responseclasses.lookuplocation.LocationItemResponse;

/**
 * Interface for extracting data from the OpenWeatherMap API.
 */
public interface iAPI {
    
    /**
     * Returns coordinates for a location.
     * @param loc Name of the location for which coordinates should be fetched.
     * @return a list of search results.
     */
    public Result<ArrayList<LocationItemResponse>> lookUpLocation(String loc);
    
    /**
     * Returns the current weather for the given coordinates.
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return current weather.
     */
    public Result<CurrentWeatherResponse> getCurrentWeather(double lat, double lon);

    /**
     * Returns a forecast for the given coordinates.
     * @param lat The latitude of the location.
     * @param lon The longitude of the location.
     * @return String.
     */
    public Result<DailyForecastResponse> getForecast(double lat, double lon);

    public Result<HourlyForecastResponse> getHourlyForecast(double lat, double lon);
}
