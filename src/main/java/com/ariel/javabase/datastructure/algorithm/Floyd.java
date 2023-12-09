package com.ariel.javabase.datastructure.algorithm;

/**
 * 弗洛伊德算法
 *
 */
public class Floyd {

    private char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
    private int size = vertexes.length;
    private int N = 65535;
    private int[][] edges = {
            {0, 5, 7, N, N, N, 2},
            {5, 0, N, 9, N, N, 3},
            {7, N, 0, N, 8, N, N},
            {N, 9, N, 0, N, 4, N},
            {N, N, 8, N, 0, 5, 4},
            {N, N, N, 4, 5, 0, 6},
            {2, 3, N, N, 4, 6, 0},
    };

    public int[][] todo() {
        // i=中继点
        for (int i = 0; i < size; i++) {
            // j=行
            for (int j = 0; j < size; j++) {
                // k=列
                for (int k = 0; k < size; k++) {
                    int temp = edges[j][i] + edges[i][k];
                    if (temp < edges[j][k]) {
                        edges[j][k] = temp;
                    }
                }
            }
        }
        return edges;
    }

}
