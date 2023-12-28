package com.ariel.java.base.reflection;

import com.ariel.java.base.reflection.a.D;
import com.ariel.java.base.reflection.a.F;
import org.junit.Assert;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射获取注解信息
 * <a href='project\_20230427210648\src\test\java\com\ariel\reflection\_20230428110604.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230428110604 {

    @Test
    public void testFetchAnnotation() {
        Class<F> fClass = F.class;

        // 获取类上的注解
        D d = fClass.getAnnotation(D.class);
        Assert.assertNotNull(d);

        // 获取成员变量上的注解
        for (Field field : fClass.getDeclaredFields()) {
            D annotation = field.getAnnotation(D.class);
            if (annotation != null) {
                System.out.printf("成员变量[%s]上有注解%n", field.getName());
            }
        }

        // 获取方法上的注解
        for (Method method : fClass.getDeclaredMethods()) {
            D annotation = method.getAnnotation(D.class);
            if (annotation != null) {
                System.out.printf("方法[%s]上有注解%n", method.getName());
            }

            Annotation[][] paramAnnotations = method.getParameterAnnotations();
            if (paramAnnotations.length > 0 && paramAnnotations[0].length > 0) {
                System.out.printf("方法[%s]的参数上有注解%n", method.getName());
            }
        }
    }

}

