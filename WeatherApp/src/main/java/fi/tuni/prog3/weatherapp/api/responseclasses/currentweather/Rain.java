package fi.tuni.prog3.weatherapp.api.responseclasses.currentweather;

import com.google.gson.annotations.SerializedName;

public class Rain {
    @SerializedName(value = "1h")
    public final float h1;

    public Rain(float h1) {
        this.h1 = h1;
    }

    @Override
    public String toString() {
        return "Rain [h1=" + h1 + "]";
    }
}
