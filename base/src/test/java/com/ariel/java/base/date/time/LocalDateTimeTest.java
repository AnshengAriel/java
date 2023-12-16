package com.ariel.java.base.date.time;

import org.junit.Test;

import java.time.*;
import java.time.temporal.*;
import java.util.List;

/**
 * 注意到LocalDateTime无法与时间戳进行转换，因为LocalDateTime没有时区，无法确定某一时刻。
 * 后面我们要介绍的ZonedDateTime相当于LocalDateTime加时区的组合，它具有时区，可以与long表示的时间戳进行转换。
 */
public class LocalDateTimeTest {

    @Test
    public void now() {
        LocalDate d = LocalDate.now(); // 当前日期
        LocalTime t = LocalTime.now(); // 当前时间
        LocalDateTime dt = LocalDateTime.now(); // 当前日期和时间
        System.out.println(d); // 严格按照ISO 8601格式打印
        System.out.println(t); // 严格按照ISO 8601格式打印
        System.out.println(dt); // 严格按照ISO 8601格式打印
    }

    @Test
    public void toLocal() {
        LocalDateTime dt = LocalDateTime.now(); // 当前日期和时间
        System.out.println(dt.toLocalDate()); // 严格按照ISO 8601格式打印
        System.out.println(dt.toLocalTime()); // 严格按照ISO 8601格式打印
        System.out.println(dt); // 严格按照ISO 8601格式打印
    }

    @Test
    public void of() {
        // 指定日期和时间:
        LocalDate d2 = LocalDate.of(2019, 11, 30); // 2019-11-30, 注意11=11月
        LocalTime t2 = LocalTime.of(15, 16, 17); // 15:16:17
        LocalDateTime dt2 = LocalDateTime.of(2019, 11, 30, 15, 16, 17);
        LocalDateTime dt3 = LocalDateTime.of(d2, t2);
        System.out.println(dt2);
        System.out.println(dt3);
    }

    @Test
    public void parse() {
        // 因为严格按照ISO 8601的格式，因此，将字符串转换为LocalDateTime就可以传入标准格式
        LocalDateTime dt = LocalDateTime.parse("2019-11-19T15:16:17");
        LocalDate d = LocalDate.parse("2019-11-19");
        LocalTime t = LocalTime.parse("15:16:17");
        System.out.println(dt);
        System.out.println(d);
        System.out.println(t);
    }

    @Test
    public void plusOrMinus() {
        LocalDateTime dt = LocalDateTime.of(2019, 10, 26, 20, 30, 59);
        System.out.println(dt);
        // 加5天减3小时:
        LocalDateTime dt2 = dt.plusDays(5).minusHours(3);
        System.out.println(dt2); // 2019-10-31T17:30:59
        // 减1月:
        LocalDateTime dt3 = dt2.minusMonths(1);
        System.out.println(dt3); // 2019-09-30T17:30:59
    }

    @Test
    public void withDateTime() {
        LocalDateTime dt = LocalDateTime.of(2019, 10, 26, 20, 30, 59);
        System.out.println(dt);
        // 日期变为31日:
        LocalDateTime dt2 = dt.withDayOfMonth(31);
        System.out.println(dt2); // 2019-10-31T20:30:59
        // 月份变为9:
        LocalDateTime dt3 = dt2.withMonth(9);
        System.out.println(dt3); // 2019-09-30T20:30:59
    }

    @Test
    public void withTemporal() {
        // 本月第一天0:00时刻:
        LocalDateTime firstDay = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        System.out.println(firstDay);

        // 本月最后1天:
        LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(lastDay);

        // 下月第1天:
        LocalDate nextMonthFirstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println(nextMonthFirstDay);

        // 本月第1个周一:
        LocalDate firstWeekday = LocalDate.now().with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        System.out.println(firstWeekday);

        LocalDate yesterday = LocalDate.now().with(temporal -> temporal.minus(1, ChronoUnit.DAYS));
        System.out.println(yesterday);
    }

    @Test
    public void isBeforeOrAfter() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime target = LocalDateTime.of(2019, 11, 19, 8, 15, 0);
        System.out.println(now.isBefore(target));
        System.out.println(LocalDate.now().isBefore(LocalDate.of(2019, 11, 19)));
        System.out.println(LocalTime.now().isAfter(LocalTime.parse("08:15:00")));
    }

}
