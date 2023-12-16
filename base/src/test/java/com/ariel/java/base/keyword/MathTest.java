package com.ariel.java.base.keyword;

import org.junit.Test;

public class MathTest {

    @Test
    public void shift() {
        int i = 0xffffffff;
        System.out.println("i = " + Integer.toHexString(i));
        System.out.println("i << 1 = " + Integer.toHexString(i << 1));
        System.out.println("i >> 1 = " + Integer.toHexString(i >> 1));
        // 无符号右移，高位补0，其实无符号就是绑定了0
        System.out.println("i >>> 1 = " + Integer.toHexString(i >>> 1));
    }
}
