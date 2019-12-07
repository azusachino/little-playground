package cn.az.code.trick;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * The type Date relate.
 *
 * @author Liz
 * @version 2019 /12/07
 */
public class DateTrick {

    public static void main(String[] args) {
        System.out.println(getBetween(1,10));
    }
    /**
     * 获取一段时间的每一天日期
     *
     * @param start the start
     * @param end   the end
     * @return between date
     */
    public static List<String> getBetweenDate(String start, String end) {
        List<String> list = new ArrayList<>();
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 1) {
            return list;
        }
        Stream.iterate(startDate, d -> d.plusDays(1)).limit(distance + 1).forEach(f -> list.add(f.toString()));
        return list;
    }

    public static List<String> getBetween(int l, int r) {
        List<String> res = new ArrayList<>();
        Stream.iterate(l, n -> n+1).limit(r).forEach(f -> res.add(String.valueOf(f)));
        return res;
    }


}
