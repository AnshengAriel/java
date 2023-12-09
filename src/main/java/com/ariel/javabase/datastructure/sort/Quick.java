package com.ariel.javabase.datastructure.sort;

/**
 * 快速排序，分而治之，从上到下
 * 每次拆分后，只做简单的操作，根据基准值将数组划分成三部分：小值区、等值区、大值区
 * 然后不断拆分小值区和大值区，直到不可拆分，完成整个数组的排序
 */
public class Quick {

    public void sort(int[] ints) {
        handle(ints, 0, ints.length - 1);
    }

    public void handle(int[] ints, int left, int right) {
        int temp;
        int l = left - 1, r = right, mr = left - 1;
        // 将第一个作为中间值
        int mid = ints[left];

        // 将数组中较低值放在左边，较高值放右边，中间值放最前
        while (l < r) {
            if (ints[l+1] == mid) {
                mr++;
                l++;
                ints[l] = ints[mr];
            }else if (ints[l+1] > mid) {
                temp = ints[l+1];
                ints[l+1] = ints[r];
                ints[r] = temp;
                r--;
            }else {
                l++;
            }
        }

        if (mr == right) {
            return;
        // true=存在小区，需要放回中间值
        }else if (l > mr) {
            for (; mr >= left; l--, mr--) {
                ints[mr] = ints[l];
                ints[l] = mid;
            }
        }

        if (l - left + 1 >= 3) {
            handle(ints, left, l);
        }else if (ints[left] > ints[l]) {
            temp = ints[left];
            ints[left] = ints[l];
            ints[l] = temp;
        }

        // r由虚转实
        r++;
        if (right - r + 1 >= 3) {
            handle(ints, r, right);
        }else if (ints[Math.min(r, right)] > ints[right]) {
            temp = ints[right];
            ints[right] = ints[r];
            ints[r] = temp;
        }

    }

}
