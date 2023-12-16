package com.ariel.java.base.datastructure.sort;

import org.junit.Test;

import java.util.Arrays;

public class SelectApp {

    @Test
    public void sort() {
        int[] ints = {7, 3, 5, 9, 2, 4};
        Select select = new Select();
        System.out.println(Arrays.toString(ints));
        select.sort(ints);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void timedSort() {
        int size = 80000;
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * size);
        }

        // 使用了6139ms
        Select select = new Select();
        long l = System.currentTimeMillis();
        select.sort(ints);
        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void sortExplain() {
        int size = 80000;
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * size);
        }

        // 一共比较[31 9996 0000]次，交换[169 1248]次，花费时间[10556]ms
        Select select = new Select();
        select.sortExplain(ints);
    }

}
