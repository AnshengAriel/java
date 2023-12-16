package com.ariel.java.base.dynamicproxy.cglib;

import org.assertj.core.internal.cglib.proxy.MethodInterceptor;
import org.assertj.core.internal.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

import static com.ariel.javabase.dynamicproxy.jdk.HelloInvocationHandler.getStr;

public class CglibMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.printf("proxy[%s] method[%s] args[%s]%n", o.getClass().getName(), method.toString(), getStr(args));
        methodProxy.invokeSuper(o, args);
        return null;
    }
}
