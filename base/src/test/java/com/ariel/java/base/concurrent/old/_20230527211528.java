package com.ariel.java.base.concurrent.old;

import org.junit.Test;

/**
 * [锁的可重入性](project\_20230525215602\src\test\java\com\ariel\thread\_20230527211528.java)
 * <a href='project\_20230525215602\src\test\java\com\ariel\thread\_20230527211528.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230527211528 {

    @Test
    public void test() throws InterruptedException {
        String lockA = "helloA";
        String lockB = "helloB";
        synchronized (lockA) {
            System.out.println("hello 1");
            synchronized (lockA) {
                System.out.println("hello 2");
            }
            synchronized (lockB) {
                System.out.println("hello 3");
            }
        }
    }
}
