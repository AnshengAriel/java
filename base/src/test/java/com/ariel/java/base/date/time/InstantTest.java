package com.ariel.java.base.date.time;

import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class InstantTest {

    @Test
    public void getEpochSecond() {
        Instant instant = Instant.now();
        System.out.println(instant);
        System.out.println(instant.getEpochSecond());
    }
    @Test
    public void toEpochMilli() {
        Instant instant = Instant.now();
        System.out.println(instant);
        System.out.println(instant.toEpochMilli());
    }

    @Test
    public void atZone() {
        // 以指定时间戳创建Instant:
        Instant ins = Instant.ofEpochSecond(1568568760);
        ZonedDateTime zdt = ins.atZone(ZoneId.systemDefault());
        System.out.println(zdt); // 2019-09-16T01:32:40+08:00[Asia/Shanghai]
    }

}
