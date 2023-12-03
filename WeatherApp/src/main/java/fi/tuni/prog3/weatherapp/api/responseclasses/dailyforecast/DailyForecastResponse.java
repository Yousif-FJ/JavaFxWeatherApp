package fi.tuni.prog3.weatherapp.api.responseclasses.dailyforecast;

import java.util.List;

public class DailyForecastResponse {
    public final int timeShift;
    public final List<DailyForecastItem> list;
    
    public DailyForecastResponse(int timeShift, List<DailyForecastItem> list) {
        this.timeShift = timeShift;
        this.list = list;
    }

    @Override
    public String toString() {
        return "DailyForecastResponse [timeShift=" + timeShift + ", list=" + list + "]";
    }
    
}
