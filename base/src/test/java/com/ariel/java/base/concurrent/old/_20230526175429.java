package com.ariel.java.base.concurrent.old;

import org.junit.Test;


/**
 * [死锁](project\_20230525215602\src\test\java\com\ariel\thread\_20230526175429.java)
 * <a href='project\_20230525215602\src\test\java\com\ariel\thread\_20230526175429.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230526175429 {

    @Test
    public void test() throws InterruptedException {
        String lockA = "lock-a", lockB = "lock-b";
        Thread threadA = new Thread(() -> {
            synchronized (lockA) {
                sleep();
                synchronized (lockB) {
                    System.out.println("无法到达的语句");
                }
            }
        });
        Thread threadB = new Thread(() -> {
            synchronized (lockB) {
                sleep();
                synchronized (lockA) {
                    System.out.println("无法到达的语句");
                }
            }
        });
        threadA.start();
        threadB.start();
        Thread.sleep(2000000);
        // 可以通过jconsole控制台查看发生死锁的线程
        System.out.printf("Thread[%s] State[%s]%n", threadA.getName(), threadA.getState());
        System.out.printf("Thread[%s] State[%s]%n", threadB.getName(), threadB.getState());
    }

    public static void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
