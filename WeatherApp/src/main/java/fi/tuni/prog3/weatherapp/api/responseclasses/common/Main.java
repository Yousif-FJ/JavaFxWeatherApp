package fi.tuni.prog3.weatherapp.api.responseclasses.common;

public class Main {
    public final float temp;
    public final float feels_like;
    public final float temp_min;
    public final float temp_max;
    public final float pressure;
    public final float humidity;
    public final float sea_level;
    public final float grnd_level;

    public Main(float temp, float feels_like, float temp_min, float temp_max, float pressure, float humidity,
            float sea_level, float grnd_level) {
        this.temp = temp;
        this.feels_like = feels_like;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.pressure = pressure;
        this.humidity = humidity;
        this.sea_level = sea_level;
        this.grnd_level = grnd_level;
    }

    @Override
    public String toString() {
        return "Main [temp=" + temp + ", feels_like=" + feels_like + ", temp_min=" + temp_min + ", temp_max=" + temp_max
                + ", pressure=" + pressure + ", humidity=" + humidity + ", sea_level=" + sea_level + ", grnd_level="
                + grnd_level + "]";
    }
}
