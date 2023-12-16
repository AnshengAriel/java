package com.ariel.java.base.datastructure.tree;

/**
 * 堆排序
 * 步骤：
 * 1.构建大根堆
 * 2.首尾交换
 * 3.堆顶沉底，大数上浮
 * 4.重复2和3
 */
public class HeapSort {

    public void sort(int[] ints) {
        // 构建大根堆
        int temp;
        for (int i = (ints.length >> 1) - 1, j; i >= 0; i--) {
            j = i*2+1;
            if (j + 1 < ints.length && ints[j] < ints[j+1]) {
                j++;
            }
            if (ints[i] < ints[j]) {
                temp = ints[i];
                ints[i] = ints[j];
                ints[j] = temp;
            }
        }

        for (int i = 1; i < ints.length; i++) {
            // 首尾交换
            temp = ints[0];
            ints[0] = ints[ints.length - i];
            ints[ints.length - i] = temp;
            // 堆顶沉底，大数上浮
            handleHeap(ints, ints.length - i);
        }
    }

    public void handleHeap(int[] ints, int length) {
        int temp;
        for (int i = 0, j, k = length / 2 - 1; i <= k; i = j) {
            j = i*2+1;
            if (j+1 < length && ints[j] < ints[j+1]) {
                j++;
            }
            if (ints[i] < ints[j]) {
                temp = ints[i];
                ints[i] = ints[j];
                ints[j] = temp;
            }else {
                break;
            }
        }
    }

}
