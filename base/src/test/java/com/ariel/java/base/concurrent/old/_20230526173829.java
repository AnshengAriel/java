package com.ariel.java.base.concurrent.old;

import org.junit.Test;

/**
 * [synchronized静态锁](project\_20230525215602\src\test\java\com\ariel\thread\_20230526173829.java)
 * <a href='project\_20230525215602\src\test\java\com\ariel\thread\_20230526173829.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230526173829 {

    @Test
    public void test() throws InterruptedException {
        new Thread(() -> {
            while (MyObject.synchronizedSell());
        }, "小红").start();

        new Thread(() -> {
            while (MyObject.nonSynchronizedSell());
        }, "小蓝").start();

        Thread.sleep(4000);
    }

    public static class MyObject {

        private static Integer ticket = 100;

        // 同步代码块
        public static boolean nonSynchronizedSell() {
            synchronized (MyObject.class) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.printf("%s正在售卖第%s张票%n", Thread.currentThread().getName(), ticket--);
                    return true;
                }
                return false;
            }
        }

        // 同步方法
        public static synchronized boolean synchronizedSell() {
            synchronized (MyObject.class) {
                System.out.print("可重入锁：");
            }
            if (ticket > 0) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.printf("%s正在售卖第%s张票%n", Thread.currentThread().getName(), ticket--);
                return true;
            }
            return false;
        }

    }

}
