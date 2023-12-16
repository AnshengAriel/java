package com.ariel.java.base.datastructure.sort;

/**
 * 归并排序，分而治之
 * 通过二分法将数组不断切割，回溯时合并并且排序
 */
public class Merge {

    public void sort(int[] ints) {
        merge(ints, new int[ints.length], 0, ints.length / 2, ints.length);
    }

    public void merge(int[] ints, int[] temp, int lLeft, int rLeft, int rRight) {
        int ll = lLeft;
        int rl = rLeft;
        int rr = rRight;
        if (rl - ll >= 2 && ll < (ll + rl) / 2 && (ll + rl) / 2 < rl) {
            merge(ints, temp, ll, (ll + rl) / 2, rl);
        }
        if (rr - rl >= 2 && rl < (rl + rr) / 2 && (rl + rr) / 2 < rr) {
            merge(ints, temp, rl, (rl + rr) / 2, rr);
        }

        int index = ll;
        while (index < rRight) {
            for (; ll < rLeft && (rl == rRight || ints[ll] <= ints[rl]); ll++, index++) {
                temp[index] = ints[ll];
            }
            for (; rl < rRight && (ll == rLeft || ints[rl] <= ints[ll]); rl++, index++) {
                temp[index] = ints[rl];
            }
        }

        for (; lLeft < rRight; lLeft++) {
            ints[lLeft] = temp[lLeft];
        }
    }
}
