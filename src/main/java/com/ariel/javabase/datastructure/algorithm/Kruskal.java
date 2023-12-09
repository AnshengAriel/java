package com.ariel.javabase.datastructure.algorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 克鲁斯卡尔算法
 * 先对边进行排序，再从小到大输出，不要构成回环
 */
public class Kruskal {

    private char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
    private int size = vertexes.length;
    private int[][] edges = {
            {0, 5, 7, 0, 0, 0, 2},
            {5, 0, 0, 9, 0, 0, 3},
            {7, 0, 0, 0, 8, 0, 0},
            {0, 9, 0, 0, 0, 4, 0},
            {0, 0, 8, 0, 0, 5, 4},
            {0, 0, 0, 4, 5, 0, 6},
            {2, 3, 0, 0, 4, 6, 0},
    };

    {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j] == 0) {
                    edges[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }

    public int[] handle() {
        int count = 0;
        for (int i = 0; i < edges.length; i++) {
            for (int j = i; j < edges[i].length; j++) {
                if (edges[i][j] < Integer.MAX_VALUE) {
                    count++;
                }
            }
        }

        int[][] edgs = new int[count][3];
        count = 0;

        for (int i = 0; i < edges.length; i++) {
            for (int j = i; j < edges[i].length; j++) {
                if (edges[i][j] < Integer.MAX_VALUE) {
                    edgs[count][0] = i;
                    edgs[count][1] = j;
                    edgs[count][2] = edges[i][j];
                    count++;
                }
            }
        }

        Arrays.sort(edgs, Comparator.comparingInt(e -> e[2]));

        // 初始化并查集
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        int[] result = new int[size - 1];
        for (int i = 0, j = 0; i < edgs.length; i++) {
            int x = edgs[i][0];
            int y = edgs[i][1];

            int end1 = find(arr, x);
            int end2 = find(arr, y);

            if (end1 != end2) {
                result[j] = edgs[i][2];
                arr[end1] = end2;
                j++;
            }

        }

        return result;
    }

    // 并查集
    public int find(int[] arr, int i) {
        while (arr[i] != i) {
            i = arr[i];
        }
        return i;
    }

}
