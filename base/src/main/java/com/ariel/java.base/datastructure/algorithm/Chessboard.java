package com.ariel.java.base.datastructure.algorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 马踏棋盘问题
 * 使用深度优先遍历
 */
public class Chessboard {

    private final int x, y, total;
    private final int[][] chessboard;
    private final int[][] next;
    private boolean success;

    public Chessboard(int x, int y) {
        this.x = x-1;
        this.y = y-1;
        this.total = x*y;
        this.chessboard = new int[x][y];
        this.next = new int[8][3];
    }

    public void handle(int i, int j, int step) {
        chessboard[i][j] = step;
        if (step == total) {
            success = true;
            return;
        }

        step++;
        for (int[] k : nextSort(i, j)) {
            if (k[2] > 0) {
                handle(k[0], k[1], step);
            }
            if (success) {
                return;
            }
        }

        // 不成功则回溯
        if (!success) {
            chessboard[i][j] = 0;
        }
    }

    /**
     * 我们分析下速度为什么会慢，有没有优化的方法：<br/>
     *
     * 速度比较慢是因为每一次走下一步的时候有许多选择，最多的时候有8种走法，下一步的下一步也是有很多种走法。<br/>
     * 如果我们的下一步的下一步是选择走法最多的那一种，那么方法递归的次数就会增加很多。<br/>
     * 所以我们如果在下一步走的时候选择下一步的下一步走法比较少的那种走法，方法递归的次数就减少，从而达到优化的效果<br/>
     * ————————————————<br/>
     * 版权声明：本文为CSDN博主「魔舞清华-方」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/fyj13925475957/article/details/104836947
     */
    public int[][] nextSort(int i, int j) {
        nextCount(i+2, j+1, 0);
        nextCount(i+2, j-1, 1);
        nextCount(i-2, j+1, 2);
        nextCount(i-2, j-1, 3);
        nextCount(i+1, j+2, 4);
        nextCount(i+1, j-2, 5);
        nextCount(i-1, j+2, 6);
        nextCount(i-1, j-2, 7);
        Arrays.sort(next, Comparator.comparingInt(k -> k[2]));

        int[][] ints = new int[0][3];
        for (int k = 0, l = 0; k < next.length; k++) {
            if (ints.length > 0) {
                ints[k - l] = next[k];
            }else if (next[k][2] > 0) {
                ints = new int[next.length - k][3];
                ints[0] = next[k];
                l = k;
            }
        }
        return ints;
    }

    public void nextCount(int i, int j, int k) {
        int count = 0;
        if (next(i, j) > 0) {
            count++;
            count += next(i+2, j+1);
            count += next(i+2, j-1);
            count += next(i-2, j+1);
            count += next(i-2, j-1);
            count += next(i+1, j+2);
            count += next(i+1, j-2);
            count += next(i-1, j+2);
            count += next(i-1, j-2);
        }
        next[k][0] = i;
        next[k][1] = j;
        next[k][2] = count;
    }

    public int next(int i, int j) {
        return i < 0 || i > x || j < 0 || j > y || chessboard[i][j] > 0 ? 0 : 1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int[] ints : chessboard) {
            for (int i : ints) {
                builder.append(i).append("\t");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}
