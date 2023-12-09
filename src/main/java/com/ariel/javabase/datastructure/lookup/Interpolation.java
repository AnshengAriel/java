package com.ariel.javabase.datastructure.lookup;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 插值查找，权重查找
 * 假定一个数组是有序且线性递增的，假定要查找的值也满足线性分布，则可以通过中值定理估算其位置再进行查找
 */
public class Interpolation {

    public int lookup(int[] ints, int v) {
        if (ints[0] > v || ints[ints.length - 1] < v) {
            return -1;
        }
        return loop(ints, 0, ints.length - 1, v);
    }

    private int loop(int[] ints, int left, int right, int v) {
        if (left > right) {
            return -1;
        }
        // 避免除零
        if (ints[left] == ints[right]) {
            return ints[left] == v ? left : -1;
        }
        // 查询大数用BigDecimal
        int i = new BigDecimal(v - ints[left])
                .multiply(new BigDecimal(right - left))
                .divide(new BigDecimal(ints[right] - ints[left]), RoundingMode.DOWN).intValue();
//        int mid = left + (v - ints[left]) / (ints[right] - ints[left]) * (right - left);
        int mid = left + i;
        if (ints[mid] > v) {
            return loop(ints, left, mid - 1, v);
        }else if (ints[mid] < v) {
            return loop(ints, mid + 1, right, v);
        }else {
            return mid;
        }
    }
}
