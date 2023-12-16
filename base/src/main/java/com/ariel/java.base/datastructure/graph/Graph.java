package com.ariel.java.base.datastructure.graph;

import java.util.LinkedList;

/**
 * 图
 */
public class Graph {

    private int[][] edges = new int[5][5];

    private int edgeSize;

    public Graph() {

    }

    public void insert(VERTEX first, VERTEX second, int weight) {
        if (first == null || second == null ||  first.equals(second) || weight <= 0) {
            return;
        }
        int row = first.getIndex();
        int column = second.getIndex();
        edges[row][column] = weight;
        edges[column][row] = weight;
        edgeSize++;
    }

    public int size() {
        return edgeSize;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        VERTEX[] VERTICES = VERTEX.values();
        builder.append("\t");
        for (VERTEX VERTEX : VERTICES) {
            builder.append(VERTEX).append("\t");
        }
        builder.append("\n");

        for (int i = 0; i < edges.length; i++) {
            builder.append(VERTICES[i]).append("\t");
            for (int j = 0; j < edges[i].length; j++) {
                builder.append(edges[i][j]).append("\t");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * 深度优先遍历dfs
     * 递归时深度遍历，回溯后广度遍历
     */
    public void depthFirst(VERTEX vertex) {
        if (vertex.isUsed()) {
            return;
        }
        System.out.println(vertex);
        vertex.setUsed(true);
        int[] ints = edges[vertex.getIndex()];
        for (int i = vertex.getIndex(); i < ints.length; i++) {
            if (ints[i] > 0) {
                depthFirst(VERTEX.valueOf(i));
            }
        }
    }

    /**
     * 广度优先遍历bfs
     * 递归时深度遍历，回溯后广度遍历
     */
    public void boardFirst(VERTEX vertex) {
        LinkedList<VERTEX> vertices = new LinkedList<>();
        vertices.add(vertex);
        VERTEX temp;
        while (!vertices.isEmpty()) {
            temp = vertices.removeFirst();
            if (!temp.isUsed()) {
                System.out.println(temp);
                temp.setUsed(true);
            }
            // 获取当前节点temp的所有邻接点，将未遍历的点放入队列中
            int[] edge = edges[temp.getIndex()];
            for (int i = 0; i < edge.length; i++) {
                if (edge[i] > 0 && !(temp = VERTEX.valueOf(i)).isUsed()) {
                    vertices.addLast(temp);
                }
            }
        }

        // 检查孤立的点
        for (VERTEX value : VERTEX.values()) {
            if (!vertex.isUsed()) {
                boardFirst(value);
            }
        }

    }

    /**
     * 顶点
     */
    public enum VERTEX {
        A(0, false),
        B(1, false),
        C(2, false),
        D(3, false),
        E(4, false),
        ;
        private final int index;
        private boolean used;
        VERTEX(int index, boolean used){
            this.index = index;

        }

        public int getIndex() {
            return index;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public static VERTEX valueOf(int index) {
            for (VERTEX vertex : values()) {
                if (vertex.index == index) {
                    return vertex;
                }
            }
            return A;
        }
    }
}
