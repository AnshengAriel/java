package com.ariel.java.base.dynamicproxy;

import java.time.ZoneId;

public class HelloServiceImpl implements HelloService {

    public final ZoneId zoneId = ZoneId.systemDefault();

    public HelloServiceImpl() {
        System.out.println("HelloServiceImpl#<init>");
    }

    @Override
    public Integer add(Integer x, Integer y) {
        System.out.printf("HelloServiceImpl#add: %d + %d = %d%n", x, y, (x + y));
        return x + y;
    }

}
