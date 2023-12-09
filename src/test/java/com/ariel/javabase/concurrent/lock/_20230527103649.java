package com.ariel.javabase.concurrent.lock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.LongAdder;

/**
 * [分段锁LongAdder的性能](project\_20230526213304\src\test\java\com\ariel\lock\_20230527103649.java)
 * <a href='project\_20230526213304\src\test\java\com\ariel\lock\_20230527103649.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230527103649 {

    @Test
    public void testA() throws InterruptedException {
        // LongAdder: From[100 0000] To[0] Take[34]ms
        avgAcc(100_0000);
    }

    @Test
    public void testB() throws InterruptedException {
        // LongAdder: From[1 0000 0000] To[0] Take[127]ms
        avgAcc(1_0000_0000);
    }

    @Test
    public void testC() throws InterruptedException {
        // LongAdder: From[10 0000 0000] To[0] Take[1023]ms
        avgAcc(10_0000_0000);
    }

    @Test
    public void testD() throws InterruptedException {
        // LongAdder: From[100 0000] To[0] Take[54]ms
        randomAcc(100_0000);
    }

    @Test
    public void testE() throws InterruptedException {
        // LongAdder: From[1 0000 0000] To[0] Take[3515]ms
        randomAcc(1_0000_0000);
    }

    @Test
    public void testF() throws InterruptedException {
        // LongAdder: From[10 0000 0000] To[0] Take[35685]ms
        randomAcc(10_0000_0000);
    }

    public static void avgAcc(int x) throws InterruptedException {
        LongAdder longAdder = new LongAdder();
        longAdder.add(x);
        int threadNum = 10, batch = x / threadNum;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        Runnable runnable = () -> {
            for (int i = 0; i < batch; i++) {
                longAdder.decrement();
            }
            countDownLatch.countDown();
        };

        long l = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            new Thread(runnable).start();
        }

        countDownLatch.await();

        System.out.printf("LongAdder: From[%s] To[%s] Take[%s]ms", x, longAdder.longValue(), System.currentTimeMillis() - l);
    }

    public static void randomAcc(int x) throws InterruptedException {
        int threadNum = 10;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        LongAdder longAdder = new LongAdder();
        longAdder.add(x);
        Runnable runnable = () -> {
            while (longAdder.longValue() > 0) {
                longAdder.decrement();
                if (longAdder.longValue() < 0) {
                    longAdder.increment();
                }
            }
            countDownLatch.countDown();
        };

        long l = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            new Thread(runnable).start();
        }

        countDownLatch.await();

        System.out.printf("LongAdder: From[%s] To[%s] Take[%s]ms", x, longAdder.longValue(), System.currentTimeMillis() - l);
    }

}
