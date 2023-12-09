package com.ariel.javabase.dynamicproxy.cglib;

import org.assertj.core.internal.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * 对目标方法进行拦截时，CallbackFilter可以针对不同方法采用不同的拦截器，但只能拦截一次
 */
public class CglibCallbackFilter implements CallbackFilter {

    /**
     * 过滤方法
     * @param method 需要拦截的方法
     * @return 处理此方法的拦截器索引，如果索引超出拦截器数组上限就会抛出异常
     */
    @Override
    public int accept(Method method) {
        // 不处理Object类中的方法，1是NoOp拦截器
        if (method.getDeclaringClass().getName().equals("java.lang.Object")) {
            return 1;
        }
        return 0;
    }
}
