package com.ariel.java.base.datastructure.lookup;

import com.ariel.java.base.datastructure.sort.Shell;
import org.junit.Test;

public class InterpolationApp {

    @Test
    public void lookup() {
        int[] ints = {1, 4, 6, 8, 12, 34, 56, 78};
        Interpolation interpolation = new Interpolation();
        interpolation.lookup(ints, 12);
    }

    @Test
    public void timedLookup() {
        int size = 800000;
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * size);
        }

        Shell shell = new Shell();
        shell.bubbleSort(ints);

        // 使用了1ms
        Interpolation interpolation = new Interpolation();
        long l = System.currentTimeMillis();
        int lookup = interpolation.lookup(ints, ints[size / 2]);
        System.out.printf("查找目标[%s] 查找结果[%s] 查找时间[%s]", size / 2, lookup, System.currentTimeMillis() - l);

    }

}
