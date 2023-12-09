package com.ariel.javabase.dynamicproxy.cglib;

import com.ariel.javabase.dynamicproxy.HelloServiceImpl;
import org.assertj.core.internal.cglib.proxy.LazyLoader;

/**
 * 返回一个代理目标的单例对象，生成目标对象时使用了单例模式
 */
public class CglibLazyLoader implements LazyLoader {
    @Override
    public Object loadObject() throws Exception {
        return new HelloServiceImpl();
    }
}
