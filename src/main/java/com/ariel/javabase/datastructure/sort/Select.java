package com.ariel.javabase.datastructure.sort;

/**
 * 选择排序，抓小放大
 * 遍历数组，查找最小值放到前面，然后依次操作，直到结束
 * 和冒泡排序相比，选择排序时间复杂度更高，但赋值操作次数更少，对于完全随机数组来说速度优于冒泡
 */
public class Select {

    public void sort(int[] ints) {
        int temp;
        int index;
        for (int i = 0; i < ints.length - 1; i++) {
            temp = ints[i];
            index = i;
            for (int j = i + 1; j < ints.length; j++) {
                if (ints[j] < temp) {
                    temp = ints[j];
                    index = j;
                }
            }
            if (index != i) {
                ints[index] = ints[i];
                ints[i] = temp;
            }
        }
    }

    public void sortExplain(int[] ints) {
        long countFor = 0, countOpr = 0, l = System.currentTimeMillis();

        int temp;
        int index;
        for (int i = 0; i < ints.length - 1; i++) {
            temp = ints[i];
            index = i;
            for (int j = i + 1; j < ints.length; j++) {
                countFor++;
                if (ints[j] < temp) {
                    countOpr+=2;
                    temp = ints[j];
                    index = j;
                }
            }
            if (index != i) {
                countOpr+=2;
                ints[index] = ints[i];
                ints[i] = temp;
            }
        }

        System.out.printf("一共比较[%s]次，交换[%s]次，花费时间[%s]ms", countFor, countOpr, System.currentTimeMillis() - l);
    }

}
