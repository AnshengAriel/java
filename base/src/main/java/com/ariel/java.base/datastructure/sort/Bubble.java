package com.ariel.java.base.datastructure.sort;

/**
 * 冒泡排序，抓大放小
 * 使用两个指针，对于相邻元素逆序则交换，复杂度是n+(n-1)+(n-2)+...+1
 * 优点是可能提前终止循环，节省时间
 * 优化：当某次遍历时发现没有发生交换，可以提前结束
 */
public class Bubble {

    public int[] sort(int[] ints) {
        // true=发生了交换，重置标记进行下一次遍历，否则提前退出
        boolean flag = false;
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length - i - 1; j++) {
                if (ints[j] > ints[j + 1]) {
                    flag = true;
                    int temp = ints[j];
                    ints[j] = ints[j+1];
                    ints[j+1] = temp;
                }
            }
            if (flag) {
                flag = false;
            }else {
                break;
            }
        }
        return ints;
    }

    public void sortExplain(int[] ints) {
        long countFor = 0, countOpr = 0, l = System.currentTimeMillis();
        // true=发生了交换，重置标记进行下一次遍历，否则提前退出
        boolean flag = false;
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints.length - i - 1; j++, countFor++) {
                if (ints[j] > ints[j + 1]) {
                    countOpr+=4;
                    flag = true;
                    int temp = ints[j];
                    ints[j] = ints[j+1];
                    ints[j+1] = temp;
                }
            }
            if (flag) {
                flag = false;
            }else {
                break;
            }
        }

        System.out.printf("一共遍历[%s]次，赋值[%s]次，花费时间[%s]ms", countFor, countOpr, System.currentTimeMillis() - l);
    }

}
