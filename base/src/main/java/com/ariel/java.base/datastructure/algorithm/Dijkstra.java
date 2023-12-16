package com.ariel.java.base.datastructure.algorithm;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 迪杰斯特拉算法，求最短路径问题
 */
public class Dijkstra {

    private char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
    private int size = vertexes.length;
    private int[][] edges = {
            {0, 12, 0, 0, 0, 16, 14},
            {12, 0, 10, 0, 0, 7, 0},
            {0, 10, 0, 3, 5, 6, 0},
            {0, 0, 3, 0, 4, 0, 0},
            {0, 0, 5, 4, 0, 2, 8},
            {16, 7, 6, 0, 2, 0, 9},
            {14, 0, 0, 0, 8, 9, 0},
    };

    {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j] == 0) {
                    edges[i][j] = Integer.MAX_VALUE;
                }else {
//                    System.out.printf("%s->%s[%s]%n", vertexes[i], vertexes[j], edges[i][j]);
                }
            }
        }
    }

    public ArrayList<Vertex> handle(char start) {
        int st = 0;
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == start) {
                st = i;
                break;
            }
        }

        ArrayList<Vertex> all = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            if (i != st) {
                all.add(new Vertex(i, vertexes[i], edges[i][st]));
            }
        }
        ArrayList<Vertex> visited = new ArrayList<>(size);
        visited.add(new Vertex(st, vertexes[st], 0));

        Comparator<Vertex> comparator = Comparator.comparingInt(a -> a.distance);
        while (!all.isEmpty()) {
            all.sort(comparator);
            Vertex removed = all.remove(0);
            visited.add(removed);
            for (Vertex vertex : all) {
                int dist = edges[removed.index][vertex.index];
                // true=此顶点是邻接点，可以作处理
                if (dist != Integer.MAX_VALUE) {
                    // 旧途径和新途径取最小
                    vertex.distance = Math.min(vertex.distance, removed.distance + dist);
                }
            }
        }
        visited.sort(comparator);
        return visited;
    }

    public static class Vertex {
        int index;
        char vertex;
        int distance;

        public Vertex(int index, char vertex, int distance) {
            this.index = index;
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "index=" + index +
                    ", vertex=" + vertex +
                    ", distance=" + distance +
                    '}';
        }
    }

}
