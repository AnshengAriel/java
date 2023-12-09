package com.ariel.javabase.datastructure.sort;

/**
 * 希尔排序，预处理优化
 * 希尔对数组进行预处理操作，使用两步让数组基本有序，然后再用对数组熵值敏感的排序
 * 所以二次排序有两种：冒泡和插入，这两种排序都是数组越有序，性能越好
 */
public class Shell {

    /**
     * 交换式希尔排序，比冒泡更快
     */
    public void bubbleSort(int[] ints) {
        int temp;
        boolean flag;
        // i=分组组数，每次减半
        for (int i = ints.length / 2; i > 0; i /= 2) {
            // j=组号
            for (int j = 0; j < i; j++) {
                // k=遍历组j时的有序部分的索引 m=交换无变化的索引位置
                for (int k = j; k < ints.length - i; k+=i) {
                    flag = false;
                    for (int l = j; l < ints.length - i - k + j; l+=i) {
                        if (ints[l] > ints[l+i]) {
                            temp = ints[l];
                            ints[l] = ints[l+i];
                            ints[l+i] = temp;
                            if (!flag) {
                                flag = true;
                            }
                        }
                    }
                    if (!flag) {
                        break;
                    }
                }
            }
        }

    }

    /**
     * 插入式希尔排序，比插入更快
     */
    public void insertSort(int[] ints) {
        int temp;
        int index;

        for (int i = ints.length / 2; i > 0; i /= 2) {
            for (int j = i; j < ints.length; j++) {
                temp = ints[j];
                index = j % i;
                for (int k = j - i; k >= 0; k-=i) {
                    if (temp < ints[k]) {
                        ints[k+i] = ints[k];
                    }else {
                        index = k + i;
                        break;
                    }
                }
                ints[index] = temp;
            }
        }
    }

}
