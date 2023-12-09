package com.ariel.javabase.datastructure.sort;

import org.junit.Test;

import java.util.Arrays;

public class QuickApp {

    @Test
    public void sort() {
        int[] ints = {7, 3, 5, 9, 2, 4};
        Quick quick = new Quick();
        System.out.println(Arrays.toString(ints));
        quick.sort(ints);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void timedSort() {
        int size = 80000;
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * size);
        }

        // 使用了12ms
        Quick quick = new Quick();
        long l = System.currentTimeMillis();
        quick.sort(ints);
        System.out.println(System.currentTimeMillis() - l);
    }

}
