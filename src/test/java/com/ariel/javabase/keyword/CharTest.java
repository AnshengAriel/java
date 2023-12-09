package com.ariel.javabase.keyword;

import org.junit.Test;

public class CharTest {

    @Test
    public void printChar() {
        // 字符都是无符号的，因此所有位数都是数值位
        char c1 = Character.MAX_VALUE;
        int i1 = c1;
        System.out.printf("'%s' = %d(0x%s)%n", c1, i1, Integer.toHexString(i1));
    }

    @Test
    public void printAllCharValue() {
        char maxValue = Character.MAX_VALUE;
        StringBuilder builder = new StringBuilder(maxValue * 2);
        for (char i = 0; i < maxValue; i++) {
            builder.append(i).append(i % 32 == 31 ? "\n" : " ");
        }
        builder.append(maxValue);
        System.out.println(builder);
    }
}
