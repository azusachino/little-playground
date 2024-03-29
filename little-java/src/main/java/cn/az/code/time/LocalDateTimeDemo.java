package cn.az.code.time;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Stream;

import cn.az.code.util.LogUtil;

/**
 * @author Liz
 */
public class LocalDateTimeDemo {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2020, Month.DECEMBER, 31);
        LocalDateTime localDateTime = localDate.atStartOfDay();

        LogUtil.info(String.valueOf(LocalDateTime.now().get(ChronoField.DAY_OF_YEAR)));
        Duration duration = Duration.between(Instant.now(), Instant.MAX);
        LogUtil.info(duration.toString());
        LogUtil.warn(String.valueOf(new NextWorkingDay().adjustInto(LocalDateTime.now())));
        LogUtil.error("next working day" + nextWorkingDay().adjustInto(LocalDate.now()));
        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.DAY_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.CHINESE);
        LogUtil.warn(dtf.format(localDateTime));

        LocalDate day = LocalDate.of(2020, Month.FEBRUARY, 29);
        LogUtil.info(String.valueOf(day));
        LogUtil.info(String.valueOf(day.plus(4, ChronoUnit.YEARS)));
        Stream.iterate(1, s -> s + 1).limit(4)
                .forEach(s -> LogUtil.info(String.valueOf(day.plus(s, ChronoUnit.YEARS))));
        Random random = new Random();
        Stream.generate(random::nextInt).limit(10).forEach(System.out::println);
    }

    public static TemporalAdjuster nextWorkingDay() {
        return TemporalAdjusters.ofDateAdjuster(
                temporal -> {
                    DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
                    int dayToAdd;
                    if (dow == DayOfWeek.FRIDAY) {
                        dayToAdd = 3;
                    } else if (dow == DayOfWeek.SATURDAY) {
                        dayToAdd = 2;
                    } else {
                        dayToAdd = 1;
                    }
                    return temporal.plus(dayToAdd, ChronoUnit.DAYS);
                });
    }
}
