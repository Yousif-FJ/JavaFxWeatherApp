package fi.tuni.prog3.weatherapp.core.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    public static String convertUnixTimestampToTime(long unixTimestamp, long timezoneOffset) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(unixTimestamp),
                ZoneOffset.ofTotalSeconds((int) timezoneOffset));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = dateTime.format(formatter);
        return formattedTime;
    }
}
