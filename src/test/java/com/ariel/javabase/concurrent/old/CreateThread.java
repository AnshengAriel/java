package com.ariel.javabase.concurrent.old;

import org.junit.After;
import org.junit.Test;

/**
 * [创建线程的几种方式](project\_20230525215602\src\test\java\com\ariel\thread\CreateThread.java)
 */
public class CreateThread {

    @After
    public void testAfter() throws InterruptedException {
        Thread.sleep(1000 * 10);
    }

    @Test
    public void test1() {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }).start();
    }

    @Test
    public void test2() {
        new Thread(new Dog()).start();
    }

    @Test
    public void test3() {
        new Thread(new Cat()).start();
        new Thread(new Cat()).start();
        new Thread(new Cat()).start();
    }

    public static class Dog implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static class Cat implements Runnable {

        int i = 100;

        @Override
        public void run() {
            while (i >= 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    System.out.println(i--);
                }
            }
        }
    }


}
