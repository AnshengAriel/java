package com.ariel.java.base.datastructure.lookup;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 * 对于一个有序数组，先确定一个中间值，然后比较大小，如果大则找右边，小找左边，不断重复
 */
public class Binary {

    public int lookup(int[] ints, int v) {
        return loop(ints, 0, ints.length - 1, v);
    }

    private int loop(int[] ints, int left, int right, int v) {
        if (right < left) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (ints[mid] > v) {
            return loop(ints, left, mid - 1, v);
        }else if (ints[mid] < v) {
            return loop(ints, mid + 1, right, v);
        }else {
            return mid;
        }
    }

    /**
     * 非递归二分查找
     */
    public int noRecursion(int[] ints, int v) {
        int left = 0;
        int right = ints.length - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (v < ints[mid]) {
                right = mid - 1;
            }else if (v > ints[mid]){
                left = mid + 1;
            }else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 查找结果中包含多个值
     * @param ints 结果集，所有相等值的索引
     * @param v 要查找的值
     */
    public List<Integer> lookupSame(int[] ints, int v) {
        List<Integer> list = new ArrayList<>();
        loop1(ints, 0, ints.length -1, v, list);
        return list;
    }

    private void loop1(int[] ints, int left, int right, int v, List<Integer> list) {
        if (right < left) {
            return ;
        }
        int mid = (left + right) / 2;
        if (ints[mid] == v) {
            list.add(mid);
        }
        if (ints[mid] >= v) {
            loop1(ints, left, mid - 1, v, list);
        }
        if (ints[mid] <= v) {
            loop1(ints, mid + 1, right, v, list);
        }
    }
}
