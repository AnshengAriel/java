package com.ariel.java.dynamicproxy.cglib;

import com.ariel.java.dynamicproxy.HelloServiceImpl;
import org.assertj.core.internal.cglib.proxy.Dispatcher;

public class CglibDispatcher implements Dispatcher {
    @Override
    public Object loadObject() throws Exception {
        return new HelloServiceImpl();
    }
}
