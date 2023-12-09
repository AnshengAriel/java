package com.ariel.javabase.dynamicproxy.cglib;

import org.assertj.core.internal.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

import static com.ariel.javabase.dynamicproxy.jdk.HelloInvocationHandler.getAddStr;
import static com.ariel.javabase.dynamicproxy.jdk.HelloInvocationHandler.getStr;

public class CglibInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object o, Method method, Object[] args) throws Throwable {
        System.out.printf("proxy[%s] method[%s] args[%s]%n", o.getClass().getName(), method.toString(), getStr(args));
        if (method.getName().equals("add")) {
            System.out.println(getAddStr(args));
        }
        return null;
    }

}
