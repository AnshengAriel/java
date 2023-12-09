package com.ariel.javabase.concurrent.old;

import org.junit.Test;

/**
 * [中断线程](project\_20230525215602\src\test\java\com\ariel\thread\Interupt.java)
 * <a href='project\_20230525215602\src\test\java\com\ariel\thread\Interupt.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class Interupt {

    @Test
    public void test() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("running");
            }
        });
        thread.start();
        // 线程从运行态转为就绪态，但又马上恢复为运行态
        thread.interrupt();
        System.out.println("interrupted");
        Thread.sleep(10);
        // 线程中断对非阻塞线程无影响，线程状态还是RUNNABLE
        System.out.println(thread.getState());
    }

    @Test
    public void test1() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getState());
            }
        });
        thread.start();
        thread.interrupt();
        System.out.println("interrupted");
        Thread.sleep(10);
        // 线程中断对阻塞线程有影响，线程状态为TERMINATED
        System.out.println(thread.getState());
    }

}
