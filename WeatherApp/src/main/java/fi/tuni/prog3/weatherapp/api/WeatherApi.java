package fi.tuni.prog3.weatherapp.api;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.GsonBuilder;

import fi.tuni.prog3.weatherapp.api.responseclasses.currentweather.CurrentWeatherResponse;

public class WeatherApi implements iAPI {
    private static final String API_KEY = "2227240fcd84ab7f0f7f86e82b0aabe0";
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final GsonBuilder gsonBuilder = new GsonBuilder(); 
    private final UnitType unitType;

    public WeatherApi(UnitType unitType){
        this.unitType = unitType;
    }

    @Override
    public Result<String> lookUpLocation(String loc) {
        String url = "http://api.openweathermap.org/data/2.5/weather?q=" + loc + "&appid=" + API_KEY
                    + "&units=" + unitType.value;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            return Result.success(response.body());
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

            var gson = gsonBuilder.create();
            var resultText = response.body();
            var result = gson.fromJson(resultText, CurrentWeatherResponse.class);

            return Result.success(result);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("Request to get data");
        }
    }

    @Override
    public Result<String> getForecast(double lat, double lon) {
        String url = "http://api.openweathermap.org/data/2.5/forecast?lat=" + lat + "&lon="
                    + lon + "&appid=" + API_KEY + "&units=" + unitType.value;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());            

            return Result.success(response.body());
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("Failed to get data");
        }
    }
}
