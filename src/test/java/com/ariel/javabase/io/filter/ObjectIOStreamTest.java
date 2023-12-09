package com.ariel.javabase.io.filter;

import com.ariel.javabase.io.UserVo;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ObjectIOStreamTest {

    @Test
    public void write() {
        System.out.println(Arrays.toString(getObjectBytes(getUser())));
    }

    @Test
    public void read() {
        byte[] bytes = getObjectBytes(getUser());
        try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            Object o = in.readObject();
            System.out.println("o = " + o);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deserializeObject() throws ReflectiveOperationException {
        // 通过反序列化流直接获取类对象，不需要通过构造函数，因此可能会丢失数据
        ObjectStreamClass aClass = ObjectStreamClass.lookup(UserVo.class);
        Method method = ObjectStreamClass.class.getDeclaredMethod("newInstance");
        method.setAccessible(true);
        Object o = method.invoke(aClass);
        System.out.println(o);
    }

    private byte[] getObjectBytes(Object o) {
        ByteArrayOutputStream byteOut;
        try (ObjectOutputStream out = new ObjectOutputStream(byteOut = new ByteArrayOutputStream())) {
            out.writeObject(o);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return byteOut.toByteArray();
    }

    private UserVo getUser() {
        return UserVo.valueOf(111, "zs");
    }

}
