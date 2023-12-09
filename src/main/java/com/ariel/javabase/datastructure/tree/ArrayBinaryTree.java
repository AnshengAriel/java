package com.ariel.javabase.datastructure.tree;

/**
 * 顺序存储二叉树，可以转成数组的形式
 */
public class ArrayBinaryTree {

    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    /**
     * 前序遍历
     * @param i 父节点位置
     */
    public void preLookup(int i) {
        System.out.println(arr[i]);
        if (i * 2 + 1 < arr.length) {
            preLookup(i * 2 + 1);
        }
        if (i * 2 + 2 < arr.length) {
            preLookup(i * 2 + 2);
        }
    }

    /**
     * 中序遍历
     * @param i 父节点位置
     */
    public void midLookup(int i) {
        if (i * 2 + 1 < arr.length) {
            midLookup(i * 2 + 1);
        }
        System.out.println(arr[i]);
        if (i * 2 + 2 < arr.length) {
            midLookup(i * 2 + 2);
        }
    }

    /**
     * 后序遍历
     * @param i 父节点位置
     */
    public void postLookup(int i) {
        if (i * 2 + 1 < arr.length) {
            postLookup(i * 2 + 1);
        }
        if (i * 2 + 2 < arr.length) {
            postLookup(i * 2 + 2);
        }
        System.out.println(arr[i]);
    }

    /**
     * 前序遍历
     * @param i 父节点位置
     * @param j 与子节点相对距离
     */
    public void preLookup(int i, int j) {
        if (j == 0) {
            System.out.println(arr[i]);
            return;
        }
        int k = j >> 1;
        System.out.println(arr[i]);
        preLookup(i-1-k, k);
        preLookup(i+1+k, k);
    }

    /**
     * 中序遍历
     * @param i 父节点位置
     * @param j 与子节点相对距离
     */
    public void midLookup(int i, int j) {
        if (j == 0) {
            System.out.println(arr[i]);
            return;
        }
        int k = j >> 1;
        preLookup(i-1-k, k);
        System.out.println(arr[i]);
        preLookup(i+1+k, k);
    }

    /**
     * 后序遍历
     * @param i 父节点位置
     * @param j 与子节点相对距离
     */
    public void postLookup(int i, int j) {
        if (j == 0) {
            System.out.println(arr[i]);
            return;
        }
        int k = j >> 1;
        preLookup(i-1-k, k);
        preLookup(i+1+k, k);
        System.out.println(arr[i]);
    }

}
