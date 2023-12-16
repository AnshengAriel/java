package com.ariel.java.base.datastructure.string;

import org.junit.Test;

import java.util.Arrays;

public class KmpApp {

    @Test
    public void next() {
        Kmp kmp = new Kmp();
        // ABCABDGGABCABCHHABCABDGGABCABD
        // ABCABDGGABCABCHHABCABDGGABCABD
        // ABCABD ABCABCGABCABCF
        // AB
        // 模式串ABCDABCGABCDABCF的公共串为ABCDABC(j=7)，公共串中又包含子串ABC(j=next(j-1)=3)，因此等式左j是子串长度，右j为公共串长度，debug所得，原理不详
        int[] next = kmp.next("ABCDABCGABCDABCF");
        System.out.println(Arrays.toString(next));
        System.out.println(next.length);
    }

    @Test
    public void indexOf() {
        Kmp kmp = new Kmp();
        int index = kmp.indexOf("ABCABDGGABCABCHHABCABDGGABCABD", "CABC");
        System.out.println("index = " + index);
    }

}
