package com.ariel.javabase.concurrent.old;

import org.junit.Test;

/**
 * [synchronized非静态锁](project\_20230525215602\src\test\java\com\ariel\thread\_20230526171553.java)
 * <a href='project\_20230525215602\src\test\java\com\ariel\thread\_20230526171553.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230526171553 {

    @Test
    public void test() throws InterruptedException {
        MyObject obj = new MyObject();
        new Thread(() -> {
            while (obj.synchronizedSell());
        }, "小红").start();

        new Thread(() -> {
            while (obj.nonSynchronizedSell());
        }, "小蓝").start();

        Thread.sleep(4000);
    }

    public static class MyObject {

        private Integer ticket = 100;

        // 同步代码块
        public boolean nonSynchronizedSell() {
            synchronized (this) {
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
        public synchronized boolean synchronizedSell() {
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
