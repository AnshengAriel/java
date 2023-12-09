package com.ariel.javabase.datastructure.lookup;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 斐波那契查找，黄金分隔法
 * 比二分法更快，主要原因是计算mid值时规避了除法运算，计算规模越大优势越大
 */
public class Fibonacci {

    private int[] fib;

    private Map<Integer, Integer> fibMap;

    public Fibonacci() {
        fib = new int[30];
        fibMap = new HashMap<>(fib.length);

        fib[0] = 1;
        fib[1] = 1;
        fibMap.put(1, 1);
        for (int i = 2; i < 30; i++) {
            fib[i] = fib[i-1] + fib[i-2];
            fibMap.put(fib[i], i);
        }

    }

    public int lookup(int[] ints, int v) {
        for (int i = 0; i < fib.length; i++) {
            if (fib[i] > ints.length) {
                int[] temp = Arrays.copyOf(ints, fib[i]);
                for (int j = ints.length; j < fib[i]; j++) {
                    temp[j] = ints[ints.length-1];
                }
                return loop(temp, 0, fib[i] - 1, v);
            }
        }
        return -1;
    }

    private int loop(int[] ints, int left, int right, int v) {
        if (left == right) {
            return ints[left] == v ? left : -1;
        }
        int length = right - left + 1;
        int mid = left + fib[fibMap.get(length) - 1];

        if (ints[mid] > v) {
            return loop(ints, left, mid - 1, v);
        }else if (ints[mid] < v) {
            return loop(ints, mid, right, v);
        }else {
            return mid;
        }
    }

}
