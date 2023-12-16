package com.ariel.java.base.datastructure.tree;

import org.junit.Test;

import java.util.Arrays;

public class ArrayBinaryTreeApp {

    @Test
    public void preLookup0() {
        int length = (1 << 4) - 1;
        int[] ints = new int[length];
        for (int i = 0; i < length; i++) {
            ints[i] = i;
        }
        System.out.println(Arrays.toString(ints));
        ArrayBinaryTree tree = new ArrayBinaryTree(ints);
        tree.preLookup(0);
    }

    @Test
    public void midLookup0() {
        int length = (1 << 4) - 1;
        int[] ints = new int[length];
        for (int i = 0; i < length; i++) {
            ints[i] = i;
        }
        System.out.println(Arrays.toString(ints));
        ArrayBinaryTree tree = new ArrayBinaryTree(ints);
        tree.midLookup(0);
    }
    @Test
    public void postLookup0() {
        int length = (1 << 4) - 1;
        int[] ints = new int[length];
        for (int i = 0; i < length; i++) {
            ints[i] = i;
        }
        System.out.println(Arrays.toString(ints));
        ArrayBinaryTree tree = new ArrayBinaryTree(ints);
        tree.postLookup(0);
    }

    @Test
    public void preLookup() {
        int length = (1 << 4) - 1;
        int[] ints = new int[length];
        for (int i = 0; i < length; i++) {
            ints[i] = i;
        }
        System.out.println(Arrays.toString(ints));
        ArrayBinaryTree tree = new ArrayBinaryTree(ints);
        tree.preLookup((length - 1) >> 1, (length - 1) >> 1);
    }

    @Test
    public void midLookup() {
        int length = (1 << 4) - 1;
        int[] ints = new int[length];
        for (int i = 0; i < length; i++) {
            ints[i] = i;
        }
        System.out.println(Arrays.toString(ints));
        ArrayBinaryTree tree = new ArrayBinaryTree(ints);
        tree.midLookup((length - 1) >> 1, (length - 1) >> 1);
    }
    @Test
    public void postLookup() {
        int length = (1 << 4) - 1;
        int[] ints = new int[length];
        for (int i = 0; i < length; i++) {
            ints[i] = i;
        }
        System.out.println(Arrays.toString(ints));
        ArrayBinaryTree tree = new ArrayBinaryTree(ints);
        tree.postLookup((length - 1) >> 1, (length - 1) >> 1);
    }

}
