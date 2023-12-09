package com.ariel.javabase.concurrent.lock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * [CAS的性能](project\_20230526213304\src\test\java\com\ariel\lock\_20230527103516.java)
 * <a href='project\_20230526213304\src\test\java\com\ariel\lock\_20230527103516.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230527103516 {

    @Test
    public void testA() throws InterruptedException {
        // AtomicInteger: From[100 0000] To[0] Take[28]ms
        avgAcc(100_0000);
    }

    @Test
    public void testB() throws InterruptedException {
        // AtomicInteger: From[1 0000 0000] To[0] Take[1246]ms
        avgAcc(1_0000_0000);
    }

    @Test
    public void testC() throws InterruptedException {
        // AtomicInteger: From[10 0000 0000] To[0] Take[12627]ms
        avgAcc(10_0000_0000);
    }

    @Test
    public void testD() throws InterruptedException {
        // AtomicInteger: From[100 0000] To[0] Take[24]ms
        randomAcc(100_0000);
    }

    @Test
    public void testE() throws InterruptedException {
        // AtomicInteger: From[1 0000 0000] To[0] Take[1278]ms
        randomAcc(1_0000_0000);
    }

    @Test
    public void testF() throws InterruptedException {
        // AtomicInteger: From[10 0000 0000] To[0] Take[13181]ms
        randomAcc(10_0000_0000);
    }

    /**
     * 创建10个线程，为每个线程安排平均的任务
     */
    public static void avgAcc(int x) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(x);
        int threadNum = 10, batch = x / threadNum;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        Runnable runnable = () -> {
            for (int i = 0; i < batch; i++) {
                atomicInteger.decrementAndGet();
            }
            countDownLatch.countDown();
        };

        long l = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            new Thread(runnable).start();
        }

        countDownLatch.await();

        System.out.printf("AtomicInteger: From[%s] To[%s] Take[%s]ms", x, atomicInteger.intValue(), System.currentTimeMillis() - l);
    }

    /**
     * 创建10个线程，为每个线程安排随机的任务量，只要结果为0即可
     */
    public static void randomAcc(int x) throws InterruptedException {
        int threadNum = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        AtomicInteger atomicInteger = new AtomicInteger(x);
        Runnable runnable = () -> {
            while (atomicInteger.intValue() > 0) {
                atomicInteger.decrementAndGet();
                if (atomicInteger.intValue() < 0) {
                    // 随机CAS扣减会多扣变复，需要手动回正
                    atomicInteger.incrementAndGet();
                }
            }
            countDownLatch.countDown();
        };

        long l = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            new Thread(runnable).start();
        }

        countDownLatch.await();

        System.out.printf("AtomicInteger: From[%s] To[%s] Take[%s]ms", x, atomicInteger.intValue(), System.currentTimeMillis() - l);
    }

}
