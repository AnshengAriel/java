package com.ariel.java.base.date.util;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

    @Test
    public void date() {
        // 获取当前时间:
        Date date = new Date();
        System.out.println(date.getYear() + 1900); // 必须加上1900
        System.out.println(date.getMonth() + 1); // 0~11，必须加上1
        System.out.println(date.getDate()); // 1~31，不能加1
        // 转换为String:
        System.out.println(date.toString());
        // 转换为GMT时区:
        System.out.println(date.toGMTString());
        // 转换为本地时区:
        System.out.println(date.toLocaleString());
    }

    @Test
    public void testDateFormat() {
        // 获取当前时间 yyyy-年 MM-月 dd:-日 HH:-小时 mm-分钟 ss-秒
        Date date = new Date();
        String[] formatters = {
                "yyyy-MM-dd HH:mm:ss",
                "E MMM dd, yyyy",
                "M",
                "MM",
                "MMM",
                "MMMM",
        };
        for (String formatter : formatters) {
            System.out.println(formatter + "\t\t" + new SimpleDateFormat(formatter).format(date));
        }
    }

}
