package com.ariel.java.base.concurrent.thread;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class CountDownLatchApi {

    @Test
    public void countDown() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        countDownLatch.countDown();
        countDownLatch.await();
    }

    @Test
    public void acquire() throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("Thread线程获取许可成功");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000);
                semaphore.release();
                System.out.println("Thread线程释放许可成功");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        semaphore.acquire();
        System.out.println("main线程获取许可成功");

    }

}
