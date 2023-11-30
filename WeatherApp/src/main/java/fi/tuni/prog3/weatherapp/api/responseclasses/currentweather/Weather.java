package fi.tuni.prog3.weatherapp.api.responseclasses.currentweather;

public class Weather {
    public final Integer id;
    public final String main;
    public final String description;
    public final String icon;

    public Weather(Integer id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Weather [id=" + id + ", main=" + main + ", description=" + description + ", icon=" + icon + "]";
    }
}
