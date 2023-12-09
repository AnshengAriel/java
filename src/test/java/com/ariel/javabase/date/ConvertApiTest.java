package com.ariel.javabase.date;

import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 由于Java提供了新旧两套日期和时间的API，除非涉及到遗留代码，否则我们应该坚持使用新的API。
 * 如果需要与遗留代码打交道，如何在新旧API之间互相转换呢？
 */
public class ConvertApiTest {

    @Test
    public void Calendar2ZonedDateTime() {
        // Calendar -> Instant -> ZonedDateTime:
        Calendar calendar = Calendar.getInstance();
        Instant ins2 = calendar.toInstant();
        ZonedDateTime zdt = ins2.atZone(calendar.getTimeZone().toZoneId());
        System.out.println(zdt);
    }

    @Test
    public void Date2Instant() {
        Instant ins = new Date().toInstant();
        System.out.println(ins);
    }

    @Test
    public void Date2ZonedDateTime() {
        Instant ins = new Date().toInstant();
        ZonedDateTime zonedDateTime = ins.atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime);
    }

    @Test
    public void ZonedDateTime2Date() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        long curr = zonedDateTime.toInstant().toEpochMilli();
        Date date = new Date(curr);
        System.out.println(date);
    }

    @Test
    public void ZonedDateTime2Calendar() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        long curr = zonedDateTime.toInstant().toEpochMilli();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone(zonedDateTime.getZone()));
        calendar.setTimeInMillis(curr);
        System.out.println(calendar.getTime());
    }
}
