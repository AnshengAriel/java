package com.ariel.javabase.datastructure.array;


/**
 * 将二维数组转化为稀疏数组
 */
public class SparseArray {

    public int[][] toSparseArray(int[][] ints) {
        if (ints == null || ints.length == 0) {
            return null;
        }

        // 统计有效个数
        int count = 0;
        for (int[] i : ints) {
            for (int j : i) {
                if (j > 0) {
                    count++;
                }
            }
        }

        if (count == 0) {
            return null;
        }

        // 新建稀疏数组，第一行存储原始数组最大行列值
        int[][] result = new int[count + 1][3];
        result[0][0] = ints.length;
        result[0][1] = ints[0].length;
        int index = 1;
        for (int i = 0; i < ints.length; i++) {
            for (int j = 0; j < ints[i].length; j++) {
                int v = ints[i][j];
                if (v != 0) {
                    result[index][0] = i;
                    result[index][1] = j;
                    result[index][2] = v;
                    index++;
                }
            }
        }

        return result;

    }

    public int[][] toArray(int[][] ints) {
        if (ints == null) {
            return null;
        }
        int[][] src = new int[ints[0][0]][ints[0][1]];
        for (int i = 1; i < ints.length; i++) {
            src[ints[i][0]][ints[i][1]] = ints[i][2];
        }
        return src;
    }

    public void print(int[][] array) {
        for (int[] i : array) {
            for (int j : i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }
}
