package com.ariel.java.base.datastructure.sort;

import org.junit.Test;

import java.util.Arrays;

public class InsertApp {

    @Test
    public void sort() {
        int[] ints = {7, 3, 5, 9, 2, 4};
        Insert insert = new Insert();
        System.out.println(Arrays.toString(ints));
        insert.sort(ints);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void timedSort() {
        int size = 80000;
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * size);
        }

        // 使用了617ms
        Insert insert = new Insert();
        long l = System.currentTimeMillis();
        insert.sort(ints);
        System.out.println(System.currentTimeMillis() - l);
    }

    @Test
    public void sortExplain() {
        int size = 80000;
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * size);
        }

        // 一共比较[15 9554 2316]次，交换[15 9562 2315]次，花费时间[960]ms
        Insert insert = new Insert();
        insert.sortExplain(ints);
    }

    @Test
    public void test() {
        int size = 80000;
        int[] sorted = new int[size];
        for (int i = 0; i < sorted.length; i++) {
            sorted[i] = (int) (Math.random() * size);
        }
        int[] random = Arrays.copyOf(sorted, size);

        // 一共遍历[32 0003 9985]次，赋值[16 0352 0623]次，花费时间[1336]ms
        Insert insert = new Insert();
        insert.sort(sorted);


        int max = Integer.MAX_VALUE >> 3;
        long t1 = System.currentTimeMillis();
        boolean f = true;
        for (int i = 0; i < max; i++) {
            if (sorted[i%size] > 10000) {
                f = false;
            }
        }
        System.out.println(System.currentTimeMillis() - t1);

        long t2 = System.currentTimeMillis();
        for (int i = 0; i < max; i++) {
            if (random[i%size] > 10000) {
                f = true;
            }
        }
        System.out.println(System.currentTimeMillis() - t2);
        System.out.println(f);
    }
}
