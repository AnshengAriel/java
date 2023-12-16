package com.ariel.java.dynamicproxy.cglib;

import org.assertj.core.internal.cglib.proxy.FixedValue;

/**
 * 生成对的代理对象会返回一个固定的值
 */
public class CglibFixedValue implements FixedValue {
    @Override
    public Object loadObject() throws Exception {
        return "Hello Fixed Value";
    }
}
