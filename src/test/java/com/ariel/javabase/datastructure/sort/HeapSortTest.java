package com.ariel.javabase.datastructure.sort;

import com.ariel.javabase.datastructure.tree.HeapSort;
import org.junit.Test;

import java.util.Arrays;

public class HeapSortTest {

    @Test
    public void sort() {
        int[] ints = {7, 3, 5, 9, 2, 4};
        HeapSort heapSort = new HeapSort();
        System.out.println(Arrays.toString(ints));
        heapSort.sort(ints);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void timedSort() {
        int size = 8000000;
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * size);
        }

        // 使用了675ms
        HeapSort HeapSort = new HeapSort();
        long l = System.currentTimeMillis();
        HeapSort.sort(ints);
        System.out.println(System.currentTimeMillis() - l);
    }

}
