package com.ariel.java.base.concurrent.lock;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.CountDownLatch;

/**
 * [锁升级策略](project\_20230526213304\src\test\java\com\ariel\lock\_20230527001909.java)
 * <a href='project\_20230526213304\src\test\java\com\ariel\lock\_20230527001909.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230527001909 {

    @Test
    public void testA() {
        // JVM会在启动进程4s内对所有创建的对象加无状态锁，需要设置-XX:BiasedLockingStartupDelay=4000，当前版本默认0
        User user = new User();

        // 无锁: 001
        // 匿名偏向锁: 101 (default)
        System.out.printf("%s%n", ClassLayout.parseInstance(user).toPrintable());
    }

    @Test
    public void testB() {
        User user = new User();
        System.out.printf("偏向锁（未偏向）%n%s%n", ClassLayout.parseInstance(user).toPrintable());
        synchronized (user) {
            System.out.printf("偏向锁（已偏向，带ID）%n%s%n", ClassLayout.parseInstance(user).toPrintable());
        }
        // 偏向锁: 101  biased: 0x000000008fd2a3a2
        System.out.printf("偏向锁（已释放，仍然偏向，带ID）%n%s%n", ClassLayout.parseInstance(user).toPrintable());
    }

    @Test
    public void testC() {
        User user = new User();

        synchronized (user) {
            System.out.printf("偏向锁（已偏向，带ID）%n%s%n", ClassLayout.parseInstance(user).toPrintable());
        }

        new Thread(() -> {
            synchronized (user) {
                System.out.printf("轻量级锁00（带栈锁指针）%n%s%n", ClassLayout.parseInstance(user).toPrintable());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName());
            }
        }).start();

    }

    @Test
    public void testD() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        User user = new User();

        Runnable runnable = () -> {
            synchronized (user) {
                countDownLatch.countDown();
                System.out.printf("重量级锁fat lock，带堆锁指针%n%s%n", ClassLayout.parseInstance(user).toPrintable());
            }
        };
        // 只需要2个线程参与竞争，就会从偏向锁升级成fat lock
        for (int i = 0; i < 2; i++) {
            new Thread(runnable).start();
        }

        countDownLatch.await();
    }

}
