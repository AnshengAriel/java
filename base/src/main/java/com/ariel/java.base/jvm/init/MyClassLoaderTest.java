package com.ariel.java.base.jvm.init;

import java.lang.reflect.InvocationTargetException;

public class MyClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InterruptedException {
        String className = Printor.class.getName();
        System.out.println("className = " + className);

        MyClassLoader loader = new MyClassLoader(className);

        while (true) {
            Class<?> aClass = loader.findClass(className);
            Object o = aClass.newInstance();
            aClass.getMethod("print").invoke(o);

            Thread.sleep(5000);

        }
    }
}
