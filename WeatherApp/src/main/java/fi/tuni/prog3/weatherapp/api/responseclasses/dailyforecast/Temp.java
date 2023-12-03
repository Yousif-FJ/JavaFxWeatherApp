package fi.tuni.prog3.weatherapp.api.responseclasses.dailyforecast;

public class Temp {
    public final float day;
    public final float min;
    public final float max;
    public final float night;
    public final float eve;
    public final float morn;
    
    public Temp(float day, float min, float max, float night, float eve, float morn) {
        this.day = day;
        this.min = min;
        this.max = max;
        this.night = night;
        this.eve = eve;
        this.morn = morn;
    }

    @Override
    public String toString() {
        return "Temp [day=" + day + ", min=" + min + ", max=" + max + ", night=" + night + ", eve=" + eve + ", morn="
                + morn + "]";
    }
}
