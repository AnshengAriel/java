package com.ariel.javabase.dynamicproxy.cglib;

import com.ariel.javabase.dynamicproxy.HelloServiceImpl;
import org.assertj.core.internal.cglib.proxy.Dispatcher;

public class CglibDispatcher implements Dispatcher {
    @Override
    public Object loadObject() throws Exception {
        return new HelloServiceImpl();
    }
}
