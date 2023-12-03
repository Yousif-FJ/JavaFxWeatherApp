package fi.tuni.prog3.weatherapp.api.responseclasses.dailyforecast;

public class FeelsLike {
    public final float day;
    public final float night;
    public final float eve;
    public final float morn;
    
    public FeelsLike(float day, float night, float eve, float morn) {
        this.day = day;
        this.night = night;
        this.eve = eve;
        this.morn = morn;
    }

    @Override
    public String toString() {
        return "FeelsLike [day=" + day + ", night=" + night + ", eve=" + eve + ", morn=" + morn + "]";
    }
}
