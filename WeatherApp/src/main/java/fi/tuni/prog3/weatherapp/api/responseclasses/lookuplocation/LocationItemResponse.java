package fi.tuni.prog3.weatherapp.api.responseclasses.lookuplocation;

public class LocationItemResponse {
    public final String name;
    public final double lat;
    public final double lon;
    public final String country;
    public final String state;

    public LocationItemResponse(String name, double lat, double lon, String country, String state) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.country = country;
        this.state = state;
    }

    @Override
    public String toString() {
        return "LocationItemResponse [name=" + name + ", lat=" + lat + ", lon=" + lon + ", country=" + country
                + ", state=" + state + "]";
    }
}
