package com.ariel.javabase.dynamicproxy;

import com.ariel.javabase.dynamicproxy.jdk.HelloInvocationHandler;
import com.ariel.javabase.dynamicproxy.jdk.HelloMultiInvocationHandler;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

public class JdkDynamicProxyTest {

    @Test
    public void newProxyInstance() {
        HelloService helloService = (HelloService) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(), new Class[]{HelloService.class}, new HelloInvocationHandler());
        helloService.add(100, 200);
    }

    @Test
    public void multiProxy() {
        // 一级代理
        HelloService helloService = (HelloService) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(), new Class[]{HelloService.class}, new HelloInvocationHandler());
        // 二级代理
        helloService = (HelloService) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(), new Class[]{HelloService.class}, new HelloMultiInvocationHandler(helloService));
        // 三级代理。。。
        helloService = (HelloService) Proxy.newProxyInstance(
                ClassLoader.getSystemClassLoader(), new Class[]{HelloService.class}, new HelloMultiInvocationHandler(helloService));
        helloService.add(100, 200);
    }

    @Test
    public void generateProxyClass() {
        // 生成代理类的字节码文件
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{HelloService.class});
        String packageName = this.getClass().getPackage().getName();
        String path = ClassLoader.getSystemResource(packageName.replace('.', '/')).getFile();
        System.out.println("path = " + path);
        try (FileOutputStream out = new FileOutputStream(path + "/$Proxy0.class")) {
            out.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
