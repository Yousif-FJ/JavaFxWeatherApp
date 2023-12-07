package fi.tuni.prog3.weatherapp.api.responseclasses.common;

public class Clouds {
    public final float all;

    public Clouds(float all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "Clouds [all=" + all + "]";
    }
}
