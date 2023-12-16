package com.ariel.java.base.datastructure.algorithm;

/**
 * 普利姆算法
 * 利用最小生成树解决无向连通图中连接所有顶点最少边的问题
 */
public class Prim {

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
        int[] result = new int[size - 1];
        int[] visited = new int[size];
        visited[0] = 1;

        int min, next = 0;
        for (int i = 0; i < size - 1;) {
            min = Integer.MAX_VALUE;
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    // true=已知点和未知点所在的边有更低的权
                    if (visited[j] == 1 && visited[k] == 0 && edges[j][k] < min) {
                        min = edges[j][k];
                        next = k;
                    }
                }
            }
            if (min < Integer.MAX_VALUE) {
                System.out.println(next);
                visited[next] = 1;
                result[i] = min;
                i++;
            }
        }
        return result;
    }
}
