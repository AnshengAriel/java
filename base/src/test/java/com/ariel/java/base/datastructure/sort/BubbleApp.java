package com.ariel.java.base.datastructure.sort;

import org.junit.Test;

import java.util.Arrays;

public class BubbleApp {

    @Test
    public void sort() {
        int[] ints = {7, 3, 5, 9, 2, 4};
        Bubble bubble = new Bubble();
        System.out.println(Arrays.toString(ints));
        bubble.sort(ints);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void timedSort() {
        int size = 80000;
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * size);
        }

        // 使用了9828ms
        Bubble bubble = new Bubble();
        long l = System.currentTimeMillis();
        bubble.sort(ints);
        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void sortExplain() {
        int size = 80000;
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * size);
        }

        // 一共遍历[31 9976 6869]次，赋值[64 0180 1796]次，花费时间[10966]ms
        Bubble bubble = new Bubble();
        bubble.sortExplain(ints);
    }

}
