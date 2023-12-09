package com.ariel.javabase.datastructure.array;

import org.junit.Test;

public class SparseArrayApp {

    @Test
    public void toSparse() {
        int[][] ints = {
                {1, 2, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 6, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 6, 0},
                {0, 0, 0, 8, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 1, 0, 0},
                {0, 2, 0, 4, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 0},
        };
        // 转化为稀疏数组
        SparseArray sparseArray = new SparseArray();
        int[][] sparse = sparseArray.toSparseArray(ints);
        sparseArray.print(sparse);
    }

    @Test
    public void toArray() {
        int[][] ints = {
                {1, 2, 0, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 6, 0, 0, 0},
                {1, 0, 0, 0, 0, 0, 6, 0},
                {0, 0, 0, 8, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 1, 0, 0},
                {0, 2, 0, 4, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 0},
        };
        // 转化为稀疏数组
        SparseArray sparseArray = new SparseArray();
        int[][] sparse = sparseArray.toSparseArray(ints);
        int[][] array = sparseArray.toArray(sparse);
        sparseArray.print(array);
    }

}