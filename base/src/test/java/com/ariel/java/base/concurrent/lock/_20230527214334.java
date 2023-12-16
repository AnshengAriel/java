package com.ariel.java.base.concurrent.lock;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * [对象在内存中的布局](project\_20230526213304\src\test\java\com\ariel\lock\_20230527214334.java)
 * <a href='project\_20230526213304\src\test\java\com\ariel\lock\_20230527214334.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230527214334 {

    @Test
    public void testObject() {
        System.out.println(ClassLayout.parseInstance(new A()).toPrintable());
    }

    @Test
    public void testArrayA() {
        // 4*4个字节
        System.out.println(ClassLayout.parseInstance(
                new String[]{"a", "b", "c", "d"}
        ).toPrintable());
    }

    @Test
    public void testArrayB() {
        // 1*4个字节
        System.out.println(ClassLayout.parseInstance(
                new byte[]{1, 2, 3, 4}
        ).toPrintable());
    }

    // 8个字节的Mark Word
    // 4个字节的Class Point
    public static class A {
        // 1个字节
        byte a; boolean e;
        // 2个字节
        short b; char f;
        // 4个字节
        int c; float h; String g;
        // 8个字节
        long d; double i;
    }
}
