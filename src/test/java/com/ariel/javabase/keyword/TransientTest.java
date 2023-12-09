package com.ariel.javabase.keyword;

import org.junit.Test;

import java.io.*;

/**
 * transient测试
 */
public class TransientTest {

    private final static String PATH = "src/test/resources/user.txt";

    @Test
    public void testA() throws IOException, ClassNotFoundException {
        User user = new User();
        user.setId(100);
        user.setName("ali");
        user.setAge(18);

        try (
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(PATH));
        ) {
            outputStream.writeObject(user);
        }
    }

    @Test
    public void testB() throws IOException, ClassNotFoundException {
        try (
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PATH))
        ) {
            User result = (User) inputStream.readObject();
            // 100 正常序列化
            System.out.println("result.getId() = " + result.getId());
            // null transient所修饰的变量在序列化和反序列化中被忽略
            System.out.println("result.getName() = " + result.getName());
            // 0 static修饰的变量同样在序列化和反序列化中被忽略
            System.out.println("result.getAge() = " + result.getAge());
        }

    }
}
