package utils;

import java.time.LocalDateTime;

public class DateFormatter {
    public static String format(LocalDateTime date){
        return "" + date.getDayOfYear() + date.getYear() + date.getHour() + date.getMinute();
    }
}
