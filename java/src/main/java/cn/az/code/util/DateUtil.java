package cn.az.code.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * The type Date util.
 *
 * @author azusachino
 * @version 1.0.0
 */
public class DateUtil {

    /**
     * Gets local date time.
     *
     * @param timeStamp the time stamp
     * @return the local date time
     */
    public static LocalDateTime getLocalDateTime(long timeStamp) {
        Instant instant = Instant.ofEpochMilli(timeStamp);
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId);
    }

    /**
     * Gets last day of current month.
     *
     * @param localDateTime the local date time
     * @return the last day of current month
     */
    public static LocalDateTime getLastDayOfCurrentMonth(LocalDateTime localDateTime) {
        return localDateTime.with(TemporalAdjusters.lastDayOfMonth()).toLocalDate().atStartOfDay();
    }

    /**
     * Gets first day of current month.
     *
     * @param localDateTime the local date time
     * @return the first day of current month
     */
    public static LocalDateTime getFirstDayOfCurrentMonth(LocalDateTime localDateTime) {
        return localDateTime.with(TemporalAdjusters.firstDayOfMonth()).toLocalDate().atStartOfDay();
    }

    /**
     * Gets morning.
     *
     * @param localDateTime the local date time
     * @return the morning
     */
    public static LocalDateTime getMorning(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate().atStartOfDay();
    }

    /**
     * Gets month between.
     *
     * @param year the year
     * @return the month between
     */
    public static List<String> getMonthBetween(int year) {
        try {
            ArrayList<String> result = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

            Calendar min = Calendar.getInstance();
            Calendar max = Calendar.getInstance();
            min.setTime(sdf.parse(year + "-1"));
            max.setTime(sdf.parse(year + "-12"));

            while (min.before(max)) {
                result.add(sdf.format(min.getTime()));
                min.add(Calendar.MONTH, 1);
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
