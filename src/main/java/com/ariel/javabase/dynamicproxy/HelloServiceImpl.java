package com.ariel.javabase.dynamicproxy;

public class HelloServiceImpl implements HelloService {
    @Override
    public Integer add(Integer x, Integer y) {
        System.out.printf("HelloServiceImpl#add: %d + %d = %d%n", x, y, (x + y));
        return x + y;
    }
}
