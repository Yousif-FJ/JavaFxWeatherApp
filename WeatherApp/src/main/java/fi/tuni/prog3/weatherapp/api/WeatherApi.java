package fi.tuni.prog3.weatherapp.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fi.tuni.prog3.weatherapp.api.responseclasses.currentweather.CurrentWeatherResponse;
import fi.tuni.prog3.weatherapp.api.responseclasses.dailyforecast.DailyForecastResponse;
import fi.tuni.prog3.weatherapp.api.responseclasses.hourlyforecast.HourlyForecastResponse;
import fi.tuni.prog3.weatherapp.api.responseclasses.lookuplocation.LocationItemResponse;

public class WeatherApi implements iAPI {
    private static final String API_KEY = "345cc47980a82a58a4e7d5459f2c37ca";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final Gson gson = new GsonBuilder().create();
    private final UnitType unitType;

    public WeatherApi(UnitType unitType){
        this.unitType = unitType;
    }

    @Override
    public Result<ArrayList<LocationItemResponse>> lookUpLocation(String loc) {
        String url = "http://api.openweathermap.org/geo/1.0/direct?q=" + loc + "&appid=" + 
            API_KEY + "&limit=8";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            var resultText = response.body();

            Type listType = new TypeToken<ArrayList<LocationItemResponse>>(){}.getType();

            ArrayList<LocationItemResponse> result = gson.fromJson(resultText, listType) ;
        
            return Result.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("Failed to get data");
        }
    }

    @Override
    public Result<CurrentWeatherResponse> getCurrentWeather(double lat, double lon) {
        String url = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon="
                    + lon + "&appid=" + API_KEY + "&units=" + unitType.value;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            var resultText = response.body();
            var result = gson.fromJson(resultText, CurrentWeatherResponse.class);

            return Result.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("Request to get data");
        }
    }

    @Override
    public Result<DailyForecastResponse> getForecast(double lat, double lon) {
        String url = "http://api.openweathermap.org/data/2.5/forecast/daily?lat=" + lat + "&lon="
                    + lon + "&appid=" + API_KEY + "&units=" + unitType.value + "&lang=en&cnt=5";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());            

            var resultText = response.body();
            var result = gson.fromJson(resultText, DailyForecastResponse.class);

            return Result.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("Failed to get data");
        }
    }

    @Override
    public Result<HourlyForecastResponse> getHourlyForecast(double lat, double lon) {
        String url = "http://pro.openweathermap.org/data/2.5/forecast/hourly?lat=" + lat + "&lon="
                    + lon + "&appid=" + API_KEY + "&units=" + unitType.value;
                    
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());            

            var resultText = response.body();
            var result = gson.fromJson(resultText, HourlyForecastResponse.class);

            return Result.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("Failed to get data");
        }
    }
}
