package com.ariel.java.base.concurrent.lock;

import org.junit.Test;

/**
 * [单线程的性能](project\_20230526213304\src\test\java\com\ariel\lock\_20230526213425.java)
 * <a href='project\_20230526213304\src\test\java\com\ariel\lock\_20230526213425.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230528153219 {

    @Test
    public void testA() throws InterruptedException {
        // single thread: From[1000000] To[0] Take[2]ms
        avgAcc(100_0000);
    }

    @Test
    public void testB() throws InterruptedException {
        // single thread: From[100000000] To[0] Take[2]ms
        avgAcc(1_0000_0000);
    }

    @Test
    public void testC() throws InterruptedException {
        // single thread: From[1000000000] To[0] Take[2]ms
        avgAcc(10_0000_0000);
    }

    public static void avgAcc(int x) {
        long l = System.currentTimeMillis();
        for (int i = x; i > 0; i--);
        System.out.printf("single thread: From[%s] To[%s] Take[%s]ms", x, 0, System.currentTimeMillis() - l);
    }

}
