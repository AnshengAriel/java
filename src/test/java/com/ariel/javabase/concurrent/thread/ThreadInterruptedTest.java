package com.ariel.javabase.concurrent.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class ThreadInterruptedTest {

    @Test
    public void interruptSleep() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        thread.interrupt();
        Thread.sleep(1000);
        System.out.println(thread.getState());
    }

    @Test
    public void interruptBlocking() throws InterruptedException {
        Thread thread = new Thread(() -> {
            synchronized (this) {
                // 无法打断阻塞状态
                System.out.println("Impossible reach, But reach.");
            }
        });

        synchronized (this) {
            thread.start();
            thread.interrupt();
            Thread.sleep(1000);
            System.out.println(thread.getState());
        }
    }

}
