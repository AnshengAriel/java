package com.ariel.java.base.datastructure.sort;

import org.junit.Test;

import java.util.Arrays;

public class RadixApp {

    @Test
    public void sort() {
        int[] ints = {7, 3, 5, 9, 2, 4};
        Radix radix = new Radix();
        System.out.println(Arrays.toString(ints));
        radix.sort(ints);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void timedSort() {
        int size = 80000;
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * size);
        }

        // 使用了11ms
        Radix radix = new Radix();
        long l = System.currentTimeMillis();
        radix.sort(ints);
        System.out.println(System.currentTimeMillis() - l);
    }

}
