package com.ariel.java.base.reflection;

import com.ariel.javabase.reflection.b.DemoA;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 反射获取泛型信息
 * <a href='project\_20230427210648\src\test\java\com\ariel\reflection\_20230428112335.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230428112335 {

    @Test
    public void testA() {
        // 子类继承泛型父类，需要指定泛型，然后通过子类获取所指定的泛型
        Type genericSuperclass = DemoA.class.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Type actualTypeArgument = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0];
            System.out.println(actualTypeArgument.getTypeName());
        }
    }

}

