package com.ariel.javabase.dynamicproxy.jdk;

import com.ariel.javabase.dynamicproxy.HelloService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.StringJoiner;

/**
 * 多重代理对象
 */
public class HelloMultiInvocationHandler implements InvocationHandler {

    private final HelloService helloService;

    public HelloMultiInvocationHandler(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.printf("proxy[%s] method[%s] args[%s]%n", proxy.getClass().getName(), method.toString(), getStr(args));
        return method.invoke(helloService, args);
    }

    private static String getStr(Object[] args) {
        StringJoiner joiner = new StringJoiner(", ");
        for (Object arg : args) {
            joiner.add(arg.toString());
        }
        return joiner.toString();
    }

    private static String getAddStr(Object[] args) {
        int count = 0;
        for (Object arg : args) {
            count += (int) arg;
        }
        return String.valueOf(count);
    }
}
