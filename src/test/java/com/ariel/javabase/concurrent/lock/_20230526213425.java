package com.ariel.javabase.concurrent.lock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * [synchronized的性能](project\_20230526213304\src\test\java\com\ariel\lock\_20230526213425.java)
 * <a href='project\_20230526213304\src\test\java\com\ariel\lock\_20230526213425.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230526213425 {

    @Test
    public void testA() throws InterruptedException {
        // synchronized: From[100 0000] To[0] Take[65]ms
        avgAcc(100_0000);
    }

    @Test
    public void testB() throws InterruptedException {
        // synchronized: From[1 0000 0000] To[0] Take[4456]ms
        avgAcc(1_0000_0000);
    }

    @Test
    public void testC() throws InterruptedException {
        // synchronized: From[10 0000 0000] To[0] Take[47842]ms
        avgAcc(10_0000_0000);
    }

    @Test
    public void testD() throws InterruptedException {
        // synchronized: From[100 0000] To[0] Take[65]ms
        randomAcc(100_0000);
    }

    @Test
    public void testE() throws InterruptedException {
        // synchronized: From[1 0000 0000] To[0] Take[4243]ms
        randomAcc(1_0000_0000);
    }

    @Test
    public void testF() throws InterruptedException {
        // synchronized: From[10 0000 0000] To[0] Take[47842]ms
        randomAcc(10_0000_0000);
    }

    public static void avgAcc(int x) throws InterruptedException {
        final int[] count = {x};
        int threadNum = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        Runnable runnable = () -> {
            for (int i = 0; i < x / threadNum; i++) {
                synchronized (count) {
                    count[0]--;
                }
            }
            countDownLatch.countDown();
        };

        long l = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            new Thread(runnable).start();
        }

        countDownLatch.await();

        System.out.printf("synchronized: From[%s] To[%s] Take[%s]ms", x, count[0], System.currentTimeMillis() - l);
    }

    public static void randomAcc(int x) throws InterruptedException {
        int threadNum = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        final int[] count = {x};
        Runnable runnable = () -> {
            while (count[0] > 0) {
                synchronized (count) {
                    if (count[0] > 0) {
                        count[0]--;
                    }
                }
            }
            countDownLatch.countDown();
        };

        long l = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            new Thread(runnable).start();
        }

        countDownLatch.await();

        System.out.printf("synchronized: From[%s] To[%s] Take[%s]ms", x, count[0], System.currentTimeMillis() - l);
    }

}
