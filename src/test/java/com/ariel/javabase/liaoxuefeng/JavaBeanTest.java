package com.ariel.javabase.liaoxuefeng;

import org.junit.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class JavaBeanTest {

    @Test
    public void test() throws IntrospectionException {
        BeanInfo person = Introspector.getBeanInfo(Person.class);
        for (PropertyDescriptor propertyDescriptor : person.getPropertyDescriptors()) {
            String name = propertyDescriptor.getName();
            Method readMethod = propertyDescriptor.getReadMethod();
            Method writeMethod = propertyDescriptor.getWriteMethod();
            System.out.printf("property[%s] readMethod[%s] writeMethod[%s]%n",
                    name, getMethodName(readMethod), getMethodName(writeMethod));
        }
    }

    public static String getMethodName(Method method) {
        return method != null ? method.getName() : "null";
    }

}
