package com.ariel.java.base.datastructure.sort;

/**
 * 基数排序，最左前缀
 * 从数字的地位往高位依次进行桶排序，直到排完全部
 */
public class Radix {

    public void sort(int[] ints) {
        int[][] temps = new int[10][ints.length];
        int[] index = new int[10];

        int temp;
        int mod = 1;
        while (index[0] != ints.length) {
            // 再遍历桶，把数据按顺序拷贝到数组中
            for (int i = 0, k = 0; i < temps.length; i++) {
                for (int j = 0; j < index[i]; j++, k++) {
                    ints[k] = temps[i][j];
                }
                index[i] = 0;
            }
            // 先遍历数组，把数据按位值放入到不同的桶中
            for (int i = 0; i < ints.length; i++) {
                temp = ints[i] / mod % 10;
                temps[temp][index[temp]] = ints[i];
                index[temp]++;
            }
            // 从低位到高位不断遍历，高位权重大，遵循最左前缀原则
            mod *= 10;
        }

        for (int j = 0; j < ints.length; j++) {
            ints[j] = temps[0][j];
        }

    }

}
