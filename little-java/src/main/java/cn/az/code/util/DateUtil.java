package cn.az.code.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Date util.
 *
 * @author az
 * @version 1.0.0
 */
public class DateUtil {

    private static final ThreadLocal<SimpleDateFormat> SIMPLE_DATE_FORMAT_THREAD_LOCAL = ThreadLocal
            .withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    private DateUtil() {
        throw new Error();
    }

    public static final String YYYY_MM = "yyyy-MM";

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

    public static String getCurrentDate() {
        return SIMPLE_DATE_FORMAT_THREAD_LOCAL.get()
                .format(Date.from(LocalDateTime.now().atZone(ZoneId.of("GMT+8")).toInstant()));
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

    public static List<String> getMonthBetweenYear(int year) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(YYYY_MM);
        return Stream.iterate(1, s -> s + 1).limit(12).map(m -> LocalDate.of(year, m, 1).format(dtf))
                .collect(Collectors.toList());
    }

    /**
     * 格式化时间区间
     * 
     * @param miliseconds 毫秒
     * @return readable时间单位
     */
    public static String formatDuration(long miliseconds) {

        DecimalFormat df = new DecimalFormat("#.00");
        StringBuffer sb = new StringBuffer();

        if (miliseconds < 1000) {
            sb.append(miliseconds).append("毫秒");
        } else if (miliseconds < 60000) {
            sb.append(df.format((double) miliseconds / 1000)).append("秒");
        } else if (miliseconds < 3600000) {
            sb.append(df.format((double) miliseconds / 60000)).append("分钟");
        } else {
            sb.append(df.format((double) miliseconds / 3600000)).append("小时");
        }
        return sb.toString();
    }
}
