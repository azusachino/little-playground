package cn.az.code.time;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.*;
import java.util.Locale;

/**
 * @author Liz
 * @date 1/9/2020
 */
@Slf4j
public class LocalDateTimeDemo {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2020, Month.DECEMBER,31);
        LocalTime localTime = LocalTime.MIDNIGHT;
        LocalDateTime localDateTime = localDate.atStartOfDay();

        log.info(String.valueOf(LocalDateTime.now().get(ChronoField.DAY_OF_YEAR)));
        Duration duration = Duration.between(Instant.now(),Instant.MAX);
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
