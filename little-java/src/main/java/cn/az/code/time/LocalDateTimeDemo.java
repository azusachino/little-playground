package cn.az.code.time;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.log.Log;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * @author Liz
 * @date 1/9/2020
 */
public class LocalDateTimeDemo {

    private static Log log = Log.get();

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2020, Month.DECEMBER, 31);
        LocalTime localTime = LocalTime.MIDNIGHT;
        LocalDateTime localDateTime = localDate.atStartOfDay();

        log.info(String.valueOf(LocalDateTime.now().get(ChronoField.DAY_OF_YEAR)));
        Duration duration = Duration.between(Instant.now(), Instant.MAX);
        log.info(duration.toString());
        log.warn(String.valueOf(new NextWorkingDay().adjustInto(LocalDateTime.now())));
        log.error(String.valueOf(nextWorkingDay().adjustInto(localDate)));
        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.DAY_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.CHINESE);
        log.warn(dtf.format(localDateTime));

        LocalDate day = LocalDate.of(2020, Month.FEBRUARY, 29);
        log.info(String.valueOf(day));
        log.info(String.valueOf(day.plus(4, ChronoUnit.YEARS)));
        Stream.iterate(1, s -> s + 1).limit(4).forEach(s -> log.info(String.valueOf(day.plus(s, ChronoUnit.YEARS))));
        Stream.generate(RandomUtil::randomInt).limit(10).forEach(System.out::println);
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
                }
        );
    }
}
