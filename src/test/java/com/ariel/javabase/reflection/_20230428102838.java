package com.ariel.javabase.reflection;

import com.ariel.javabase.reflection.a.F;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 反射创建对象
 * <a href='project\_20230427210648\src\test\java\com\ariel\reflection\_20230428102838.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230428102838 {

    @Test
    public void testNewInstance() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> fClass = ClassLoader.getSystemClassLoader().loadClass("com.ariel.reflection.a.F");

        Constructor<?> constructor = fClass.getConstructor(String.class, Integer.class);
        F f = (F) constructor.newInstance("limin", 18);
        Assert.assertEquals(f.getName(), "limin");
        Assert.assertEquals(f.getAge(), Integer.valueOf(18));

        // 获取默认无参构造器，不存在或者private都将会报错
        Object o = fClass.newInstance();
        Assert.assertNotNull(o);

        // 无法直接获取私有构造器
        try {
            constructor = fClass.getConstructor(String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        // 只能间接获取私有构造器
        constructor = fClass.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        f = (F) constructor.newInstance("limin");
        Assert.assertEquals(f.getName(), "limin");
    }

}

