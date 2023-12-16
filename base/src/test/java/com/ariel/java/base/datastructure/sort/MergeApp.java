package com.ariel.java.base.datastructure.sort;

import org.junit.Test;

import java.util.Arrays;

public class MergeApp {

    @Test
    public void sort() {
        int[] ints = {7, 3, 5, 9, 2, 4};
        Merge merge = new Merge();
        System.out.println(Arrays.toString(ints));
        merge.sort(ints);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void timedSort() {
        int size = 80000;
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * size);
        }

        // 使用了36ms
        Merge merge = new Merge();
        long l = System.currentTimeMillis();
        merge.sort(ints);
        System.out.println(System.currentTimeMillis() - l);
    }

}
