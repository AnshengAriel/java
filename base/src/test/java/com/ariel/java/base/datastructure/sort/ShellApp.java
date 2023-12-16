package com.ariel.java.base.datastructure.sort;

import org.junit.Test;

import java.util.Arrays;

public class ShellApp {

    @Test
    public void bubbleSort() {
        int[] ints = {7, 3, 5, 9, 2, 4};
        Shell shell = new Shell();
        System.out.println(Arrays.toString(ints));
        shell.bubbleSort(ints);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void timedBubbleSort() {
        int size = 80000;
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * size);
        }

        // 使用了43ms
        Shell shell = new Shell();
        long l = System.currentTimeMillis();
        shell.bubbleSort(ints);
        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void insertSort() {
        int[] ints = {7, 3, 5, 9, 2, 4};
        Shell shell = new Shell();
        System.out.println(Arrays.toString(ints));
        shell.insertSort(ints);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void timedInsertSort() {
        int size = 800000;
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * size);
        }

        // 使用了141ms
        Shell shell = new Shell();
        long l = System.currentTimeMillis();
        shell.insertSort(ints);
        System.out.println(System.currentTimeMillis() - l);
    }

}
