package com.ariel.java.base.dynamicproxy;

import com.ariel.javabase.dynamicproxy.cglib.*;
import org.assertj.core.internal.cglib.core.AbstractClassGenerator;
import org.assertj.core.internal.cglib.core.DebuggingClassWriter;
import org.assertj.core.internal.cglib.proxy.Callback;
import org.assertj.core.internal.cglib.proxy.Enhancer;
import org.assertj.core.internal.cglib.proxy.NoOp;
import org.assertj.core.internal.cglib.proxy.Proxy;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;

public class CglibDynamicProxyTest {

    @Test
    public void valueOfDefault() {
        HelloService helloService = (HelloService) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(), new Class[]{HelloService.class}, new CglibInvocationHandler());
        helloService.add(1, 1);
    }

    @Test
    public void valueOfInvocationHandler() {
        valueOf(new CglibInvocationHandler());
    }

    @Test
    public void valueOfMethodInterceptor() {
        valueOf(new CglibMethodInterceptor());
    }

    @Test
    public void valueOfNoOp() {
        valueOf(NoOp.INSTANCE);
    }

    @Test
    public void valueOfFixedValue() {
        valueOf(new CglibFixedValue());
    }

    @Test
    public void valueOfLazyLoader() {
        valueOf(new CglibLazyLoader());
    }

    @Test
    public void valueOfDispatcher() {
        valueOf(new CglibDispatcher());
    }

    public void valueOf(Callback callback) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloServiceImpl.class);
        enhancer.setCallback(callback);
        HelloService helloService = (HelloService) enhancer.create();
        Integer added = helloService.add(1, 1);
        System.out.println("Test#valueOf: added = " + added);
    }

    /*---------------------------------------------生成字节码-------------------------------------------------*/

    @Test
    public void generateMethodInterceptor() throws Exception {
        generate(new CglibMethodInterceptor(), "MethodInterceptor0");
    }

    @Test
    public void generateInvocationHandler() throws Exception {
        generate(new CglibInvocationHandler(), "InvocationHandler0");
    }

    @Test
    public void generateNoOp() throws Exception {
        generate(NoOp.INSTANCE, "NoOp0");
    }

    @Test
    public void generateFixedValue() throws Exception {
        generate(new CglibFixedValue(), "FixedValue0");
    }

    @Test
    public void generateLazyLoader() throws Exception {
        generate(new CglibLazyLoader(), "LazyLoader0");
    }

    @Test
    public void generateDispatcher() throws Exception {
        generate(new CglibDispatcher(), "Dispatcher0");
    }

    public void generate(Callback callback, String num) throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloServiceImpl.class);
//        enhancer.setSuperclass(UserService.class);
        enhancer.setCallbacks(new Callback[]{callback, NoOp.INSTANCE});
        enhancer.setCallbackTypes(new Class[]{callback.getClass(), NoOp.class});
        enhancer.setCallbackFilter(new CglibCallbackFilter());
        enhancer.setUseFactory(false);

        String packageName = HelloServiceImpl.class.getPackage().getName();
        Field field = AbstractClassGenerator.class.getDeclaredField("className");
        field.setAccessible(true);
        field.set(enhancer, packageName + ".HelloServiceImpl$$EnhancerByCGLIB$$" + num);
//        field.set(enhancer, packageName + ".UserService$$EnhancerByCGLIB$$" + num);

        DebuggingClassWriter writer = new DebuggingClassWriter(2);
        enhancer.generateClass(writer);

        byte[] bytes = writer.toByteArray();

        String path = ClassLoader.getSystemResource(HelloServiceImpl.class.getPackage().getName().replace('.', '/')).getFile();
        path = path + "/HelloServiceImpl$$EnhancerByCGLIB$$"+ num +".class";
//        path = path + "/UserService$$EnhancerByCGLIB$$"+ num +".class";
        System.out.println("path = " + path);
        try (FileOutputStream out = new FileOutputStream(path)) {
            out.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
