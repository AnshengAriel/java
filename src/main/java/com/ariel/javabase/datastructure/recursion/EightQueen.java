package com.ariel.javabase.datastructure.recursion;

import java.util.Arrays;

/**
 * 八皇后问题
 * 在8×8格的国际象棋上摆放八个皇后，使其不能互相攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线上，问一共有多少种摆法。
 */
public class EightQueen {

    private final int size;

    private final int[] answer;

    private int count = 0;

    public EightQueen(int size) {
        this.size = size;
        answer = new int[size];
    }

    public void handle(int i) {
        for (int j = 0; j < size; j++) {
            boolean flag = false;
            for (int k = 0; k < i; k++) {
                // true1=此列没有放置过；true2=此列放置不与任何一子处于同一斜线。满足以上任意条件都不能放置
                if (answer[k] == j || Math.abs(answer[k] - j) == i - k) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                answer[i] = j;
                // true=已经到最后一个了
                if (i == size - 1) {
                    count++;
                    System.out.println(Arrays.toString(answer));
                    return;
                }
                handle(i + 1);
            }
        }
        if (i == 0) {
            System.out.printf("总共有[%s]种", count);
        }
    }

}
