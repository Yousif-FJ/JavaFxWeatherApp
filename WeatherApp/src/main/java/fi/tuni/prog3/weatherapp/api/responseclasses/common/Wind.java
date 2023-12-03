package fi.tuni.prog3.weatherapp.api.responseclasses.common;

public class Wind {
    public final float speed;
    public final float deg;
    public final float gust;

    public Wind(float speed, float deg, float gust) {
        this.speed = speed;
        this.deg = deg;
        this.gust = gust;
    }

    @Override
    public String toString() {
        return "Wind [speed=" + speed + ", deg=" + deg + ", gust=" + gust + "]";
    }
}
