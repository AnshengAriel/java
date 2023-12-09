package com.ariel.javabase.reflection;

import com.ariel.javabase.reflection.a.F;
import org.junit.Assert;
import org.junit.Test;

/**
 * 反射获取Class
 * <a href='project\_20230427210648\src\test\java\com\ariel\reflection\_20230427210816.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230427210816 {

    @Test
    public void testFetchClass() throws ClassNotFoundException {
        Class<F> fClass1 = F.class;
        Class<? extends F> fClass2 = new F("f", 10).getClass();
        Class<?> fClass3 = Class.forName("com.ariel.reflection.a.F");
        Class<?> fClass4 = ClassLoader.getSystemClassLoader().loadClass("com.ariel.reflection.a.F");

        Assert.assertEquals(fClass1, fClass2);
        Assert.assertEquals(fClass1, fClass3);
        Assert.assertEquals(fClass1, fClass4);
    }

}

