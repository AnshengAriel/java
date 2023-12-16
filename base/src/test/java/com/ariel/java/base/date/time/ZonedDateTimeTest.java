package com.ariel.java.base.date.time;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeTest {

    @Test
    public void now() {
        ZonedDateTime zbj = ZonedDateTime.now(); // 默认时区
        ZonedDateTime zny = ZonedDateTime.now(ZoneId.of("America/New_York")); // 用指定时区获取当前时间
        System.out.println(zbj);
        System.out.println(zny);
    }

    @Test
    public void atZone() {
        LocalDateTime ldt = LocalDateTime.of(2019, 9, 15, 15, 16, 17);
        ZonedDateTime zbj = ldt.atZone(ZoneId.systemDefault());
        ZonedDateTime zny = ldt.atZone(ZoneId.of("America/New_York"));
        System.out.println(zbj);
        System.out.println(zny);
    }

    @Test
    public void withZoneSameInstant() {
        // 以中国时区获取当前时间:
        ZonedDateTime zbj = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        // 转换为纽约时间:
        ZonedDateTime zny = zbj.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println(zbj);
        System.out.println(zny);
    }

    @Test
    public void toLocalDateTime() {
        // 以中国时区获取当前时间:
        ZonedDateTime zbj1 = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime zbj2 = ZonedDateTime.now(ZoneId.of("America/New_York"));
        // 转换为纽约时间:
        LocalDateTime ldt1 = zbj1.toLocalDateTime();
        LocalDateTime ldt2 = zbj2.toLocalDateTime();
        System.out.println(zbj1);
        System.out.println(ldt1);
        System.out.println(zbj2);
        System.out.println(ldt2);
    }

}
