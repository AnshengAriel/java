package com.ariel.javabase.concurrent.lock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * [可重入锁的性能](project\_20230526213304\src\test\java\com\ariel\lock\_20230527171032.java)
 * <a href='project\_20230526213304\src\test\java\com\ariel\lock\_20230527171032.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230527171032 {

    @Test
    public void testA() throws InterruptedException {
        // ReentrantLock: From[1000000] To[0] Fair[false] ThreadNum[1] Take[25]ms
        // ReentrantLock: From[1000000] To[0] Fair[false] ThreadNum[10] Take[55]ms
        // ReentrantLock: From[1000000] To[0] Fair[false] ThreadNum[100] Take[72]ms
        // ReentrantLock: From[1000000] To[0] Fair[false] ThreadNum[1000] Take[121]ms
        // ReentrantLock: From[1000000] To[0] Fair[true] ThreadNum[1] Take[30]ms
        // ReentrantLock: From[1000000] To[0] Fair[true] ThreadNum[10] Take[4895]ms
        // ReentrantLock: From[1000000] To[0] Fair[true] ThreadNum[100] Take[5196]ms
        // ReentrantLock: From[1000000] To[0] Fair[true] ThreadNum[1000] Take[7349]ms Speed[136]/ms
        avgAcc(100_0000, true, 1000);
    }

    @Test
    public void testB() throws InterruptedException {
        // ReentrantLock: From[100000000] To[0] Fair[false] ThreadNum[10] Take[2206]ms Speed[45330]/ms
        // ReentrantLock: From[100000000] To[0] Fair[false] ThreadNum[100] Take[2335]ms Speed[42826]/ms
        // ReentrantLock: From[100000000] To[0] Fair[false] ThreadNum[10000] Take[3586]ms Speed[27886]/ms
        // ReentrantLock: From[100000000] To[0] Fair[true] ThreadNum[100] Take[525866]ms Speed[190]/ms CPU[12.4]
        // ReentrantLock: From[100000000] To[0] Fair[true] ThreadNum[10000] Take[904708]ms Speed[110]/ms
        avgAcc(1_0000_0000, false, 100);
    }

    @Test
    public void testC() throws InterruptedException {
        // ReentrantLock: From[1000000000] To[0] Take[22571]ms
        // ReentrantLock: From[1000000000] To[0] Fair[false] ThreadNum[100] Take[22633]ms Speed[44183]/ms CPU[24.5]
        avgAcc(10_0000_0000, false, 100);
    }

    @Test
    public void testD() throws InterruptedException {
        // ReentrantLock: From[1000000] To[0] Take[53]ms
        // ReentrantLock(fair): From[1000000] To[0] Take[4501]ms
        randomAcc(100_0000);
    }

    @Test
    public void testE() throws InterruptedException {
        // ReentrantLock: From[100000000] To[0] Take[2167]ms
        randomAcc(1_0000_0000);
    }

    @Test
    public void testF() throws InterruptedException {
        // ReentrantLock: From[1000000000] To[0] Take[21065]ms
        randomAcc(10_0000_0000);
    }

    public static void avgAcc(int x, boolean fair, int threadNum) throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock(fair);
        int batch = x / threadNum;
        int[] value = {x};
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        Runnable runnable = () -> {
            for (int i = 0; i < batch; i++) {
                reentrantLock.lock();
                value[0]--;
                reentrantLock.unlock();
            }
            countDownLatch.countDown();
        };

        long l = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            new Thread(runnable).start();
        }

        countDownLatch.await();

        long t = System.currentTimeMillis() - l;
        System.out.printf("ReentrantLock: From[%s] To[%s] Fair[%s] ThreadNum[%s] Take[%s]ms Speed[%s]/ms", x, value[0], fair, threadNum, t, x/t);
    }

    public static void randomAcc(int x) throws InterruptedException {
        int threadNum = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        ReentrantLock reentrantLock = new ReentrantLock();

        int[] value = {x};
        Runnable runnable = () -> {
            while (value[0] > 0) {
                if (reentrantLock.tryLock()) {
                    try {
                        if (value[0] > 0) {
                            value[0]--;
                        }
                    } finally {
                        reentrantLock.unlock();
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

        System.out.printf("ReentrantLock: From[%s] To[%s] Take[%s]ms", x, value[0], System.currentTimeMillis() - l);
    }

}
