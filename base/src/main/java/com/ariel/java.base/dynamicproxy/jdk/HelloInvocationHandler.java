package com.ariel.java.base.dynamicproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.StringJoiner;

public class HelloInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.printf("proxy[%s] method[%s] args[%s]%n", proxy.getClass().getName(), method.toString(), getStr(args));
        if (method.getName().equals("add")) {
            System.out.println(getAddStr(args));
        }
        return null;
    }

    public static String getStr(Object[] args) {
        StringJoiner joiner = new StringJoiner(", ");
        for (Object arg : args) {
            joiner.add(arg.toString());
        }
        return joiner.toString();
    }

    public static String getAddStr(Object[] args) {
        int count = 0;
        for (Object arg : args) {
            count += (int) arg;
        }
        return String.valueOf(count);
    }
}
