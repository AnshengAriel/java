package com.ariel.java.base.datastructure.tree;

import org.junit.Test;

public class BalanceBinaryTreeApp {

    @Test
    public void add() {
        BalanceBinaryTree tree = new BalanceBinaryTree();
        for (int i = 0; i < 100; i++) {
            tree.add(i);
        }
        tree.printPretty();
    }
}
