package com.ariel.java.base.concurrent.aqs;

import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreTest {

    @Test
    public void t() throws InterruptedException {
        Semaphore semaphore = new Semaphore(10);
        for (int i = 0; i < 11; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(finalI);
                    semaphore.acquire();
                    System.out.printf("%s: %d%n", Thread.currentThread().getName(), semaphore.availablePermits());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
        Thread.sleep(12000);
    }
}
