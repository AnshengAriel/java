package com.ariel.javabase.datastructure.lookup;

import com.ariel.javabase.datastructure.sort.Shell;
import org.junit.Test;

public class FibonacciApp {

    @Test
    public void lookup() {
        int[] ints = {1, 4, 6, 8, 12, 34, 56, 78};
        Fibonacci fibonacci = new Fibonacci();
        int lookup = fibonacci.lookup(ints, 1);
        System.out.println("lookup = " + lookup);
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

        // 使用了4ms
        Fibonacci fibonacci = new Fibonacci();
        long l = System.currentTimeMillis();
        int lookup = fibonacci.lookup(ints, ints[size / 2]);
        System.out.printf("查找目标[%s] 查找结果[%s] 查找时间[%s]", size / 2, lookup, System.currentTimeMillis() - l);
    }

}
