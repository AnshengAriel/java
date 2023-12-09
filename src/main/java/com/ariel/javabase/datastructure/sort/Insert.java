package com.ariel.javabase.datastructure.sort;

/**
 * 插入排序，打牌算法
 * 将数组人为的划分成两个部分，前半部有序，后半部无序，然后不断扩增有序的部分
 * 插排适合操作整体有序局部无序的数组，这样可以尽量减少插入操作
 * 插入排序和选择相比，插入排序只对有序数组遍历，而选择排序每次都要遍历整个无序数组部分，所以总的遍历次数更少，速度更快
 */
public class Insert {

    public void sort(int[] ints) {
        int temp;
        int index;
        for (int i = 1; i < ints.length; i++) {
            temp = ints[i];
            index = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (ints[j] > temp) {
                    ints[j+1] = ints[j];
                }else {
                    index = j+1;
                    break;
                }
            }
            ints[index] = temp;
        }
    }

    public void sortExplain(int[] ints) {
        long countFor = 0, countOpr = 0, l = System.currentTimeMillis();

        int index;
        int temp;
        for (int i = 1; i < ints.length; i++, countOpr++) {
            temp = ints[i];
            index = 0;
            for (int j = i - 1; j >= 0; j--, countFor++, countOpr++) {
                if (ints[j] > temp) {
                    ints[j+1] = ints[j];
                }else {
                    index = j+1;
                    break;
                }
            }
            ints[index] = temp;
        }

        System.out.printf("一共比较[%s]次，交换[%s]次，花费时间[%s]ms", countFor, countOpr, System.currentTimeMillis() - l);
    }
}
