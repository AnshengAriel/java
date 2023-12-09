package com.ariel.javabase.concurrent.aqs;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {

    @Test
    public void await() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(finalI);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                countDownLatch.countDown();
                System.out.printf("%s: %d%n", Thread.currentThread().getName(), countDownLatch.getCount());
            }).start();
        }
        countDownLatch.await();
    }

}
