package com.ariel.javabase.concurrent.old;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * [线程的生命周期](project\_20230525215602\src\test\java\com\ariel\thread\_20230526152457.java)
 * <a href='project\_20230525215602\src\test\java\com\ariel\thread\_20230526152457.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230526152457 {

    public static final String lock = "hello lock";

    @Test
    public void testNew() {
        Thread thread = new Thread();
        System.out.printf("线程[%s]创建后状态[%s]%n", thread.getName(), thread.getState());
    }

    @Test
    public void testRunnable() {
        Thread thread = new Thread();
        thread.start();
        System.out.printf("线程[%s]运行后状态[%s]%n", thread.getName(), thread.getState());
    }

    @Test
    public void testTerminated() throws InterruptedException {
        Thread thread = new Thread();
        thread.start();
        Thread.sleep(100);
        System.out.printf("线程[%s]结束后状态[%s]%n", thread.getName(), thread.getState());
    }

    @Test
    public void testBlocked() throws InterruptedException {
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                // 被阻塞
            }
        });
        thread.start();
        synchronized (lock) {
            Thread.sleep(100);
            System.out.printf("线程[%s]阻塞后状态[%s]%n", thread.getName(), thread.getState());
        }
    }

    @Test
    public void testTimedWaitingA() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();

        // 线程[Thread-0]有限等待后状态[TIMED_WAITING]
        Thread.sleep(100);
        System.out.printf("线程[%s]定时等待后状态[%s]%n", thread.getName(), thread.getState());
    }

    @Test
    public void testTimedWaitingB() throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            // 线程[main]有限等待后状态[TIMED_WAITING]
            System.out.printf("线程[%s]定时等待后状态[%s]%n", mainThread.getName(), mainThread.getState());
        });
        thread.start();
        thread.join(1000);
    }

    @Test
    public void testWaitingB() throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            // 线程[main]无限等待后状态[WAITING]
            System.out.printf("线程[%s]无限等待后状态[%s]%n", mainThread.getName(), mainThread.getState());
        });
        thread.start();
        thread.join();
    }

    @Test
    public void testTimedWaitingC() throws InterruptedException {
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    // 定时等待，超过一定时间后自己notify()
                    lock.wait(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        Thread.sleep(100);
        // 线程[Thread-0]有限等待后状态[TIMED_WAITING]
        System.out.printf("线程[%s]定时等待后状态[%s]%n", thread.getName(), thread.getState());
    }

    @Test
    public void testWaitingC() throws InterruptedException {
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    // 无限等待，直到被notify()
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
        Thread.sleep(100);
        // 线程[Thread-0]无限等待后状态[WAITING]
        System.out.printf("线程[%s]无限等待后状态[%s]%n", thread.getName(), thread.getState());
    }

    @Test
    public void testWaitingD() throws InterruptedException {
        Thread thread = new Thread(() -> {
            LockSupport.park(this);
        });
        thread.start();
        Thread.sleep(100);
        // 线程[Thread-0]无限等待后状态[WAITING]
        System.out.printf("线程[%s]无限等待后状态[%s]%n", thread.getName(), thread.getState());
    }

    @Test
    public void testWaitingE() throws InterruptedException {
        Thread thread = new Thread(() -> {
            BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);
            try {
                queue.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        Thread.sleep(100);
        // 线程[Thread-0]无限等待后状态[WAITING]
        System.out.printf("线程[%s]无限等待后状态[%s]%n", thread.getName(), thread.getState());
    }

    @Test
    public void testTimedWaitingE() throws InterruptedException {
        Thread thread = new Thread(() -> {
            BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);
            try {
                queue.poll(1000, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        Thread.sleep(100);
        // 线程[Thread-0]定时等待后状态[TIMED_WAITING]
        System.out.printf("线程[%s]定时等待后状态[%s]%n", thread.getName(), thread.getState());
    }
}
