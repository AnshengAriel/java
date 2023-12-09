package com.ariel.javabase.designmode.structure;

import org.assertj.core.internal.cglib.proxy.Enhancer;
import org.assertj.core.internal.cglib.proxy.MethodInterceptor;
import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * 代理模式
 */
public class ProxyTest {

    @Test
    public void testStaticProxy() {
        SourceObject proxy = new SourceObject(new Target());
        proxy.name();
    }

    @Test
    public void testSdkProxy() {
        TargetInterface targetProxy = (TargetInterface) Proxy.newProxyInstance(
                Target.class.getClassLoader(),
                new Class[]{TargetInterface.class},
                (proxy, method, args) -> method.invoke(new Target(), args)
        );
        targetProxy.name();
    }

    @Test
    public void testCglibProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Target.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invoke(new Target(), objects));
        Target target = (Target) enhancer.create();
        target.name();
    }

    interface TargetInterface {
        void name();
    }

    static class Target implements TargetInterface {

        public void name() {
            System.out.println("我是被代理对象");
        }
    }

    static class SourceObject {
        private Target target;

        public SourceObject(Target target) {
            this.target = target;
        }

        public void name() {
            System.out.println("我是代理对象");
            target.name();
        }
    }
}
