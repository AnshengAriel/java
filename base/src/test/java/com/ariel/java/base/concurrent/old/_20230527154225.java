package com.ariel.java.base.concurrent.old;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * [线程的阻塞和唤醒park()和unpark()](project\_20230525215602\src\test\java\com\ariel\thread\_20230527154225.java)
 * <a href='project\_20230525215602\src\test\java\com\ariel\thread\_20230527154225.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230527154225 {

    @Test
    public void test() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread threadA = new Thread(() -> {
            System.out.println("1.线程A开始执行");
            LockSupport.park();
            System.out.println("4.线程A执行完成");
            countDownLatch.countDown();
        });
        Thread threadB = new Thread(() -> {
            System.out.println("2.线程A暂停，线程B开始执行");
            System.out.println("3.线程B执行完成，唤醒线程A");

            // park后进入WAITING状态，unpark恢复RUNNABLE，这些操作与锁无关
            sleep();
            System.out.println(threadA.getState());
            LockSupport.unpark(threadA);
            countDownLatch.countDown();
        });

        threadA.start();
        threadB.start();

        countDownLatch.await();

    }

    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
