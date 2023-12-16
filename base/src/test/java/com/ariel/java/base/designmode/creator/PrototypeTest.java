package com.ariel.java.base.designmode.creator;

import org.junit.Assert;
import org.junit.Test;

/**
 * 原型模式
 */
public class PrototypeTest {

    @Test
    public void testPrototype() throws CloneNotSupportedException {
        User user = new User();
        Assert.assertNotSame(user.clone(), user.clone());
    }

    @Test
    public void tesTimeFirst() throws CloneNotSupportedException {
        new Thread(() -> {
            User user = new User();
            for (int i = 0; i < 1000; i++) {
                user = new User();
            }
            System.out.println("先创建");
        }).start();

        new Thread(() -> {
            User user = new User();
            for (int i = 0; i < 1000; i++) {
                try {
                    user = (User) user.clone();
                } catch (CloneNotSupportedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("先克隆");
        }).start();
    }

    static class User implements Cloneable {
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

}