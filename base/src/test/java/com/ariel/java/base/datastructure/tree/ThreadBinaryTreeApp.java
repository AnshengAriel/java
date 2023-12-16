package com.ariel.java.base.datastructure.tree;

import org.junit.Test;

public class ThreadBinaryTreeApp {

    @Test
    public void preThread() {
        ThreadBinaryTree tree = new ThreadBinaryTree();
        tree.add(new BinaryTree.Node(4, "4"));
        tree.add(new BinaryTree.Node(2, "2"));
        tree.add(new BinaryTree.Node(6, "6"));
        tree.add(new BinaryTree.Node(3, "3"));
        tree.add(new BinaryTree.Node(5, "5"));
        tree.add(new BinaryTree.Node(1, "1"));
        tree.add(new BinaryTree.Node(7, "7"));
        tree.printPretty();
        tree.preThread();
        tree.midOrder();
    }

    @Test
    public void midThread() {
        ThreadBinaryTree tree = new ThreadBinaryTree();
        tree.add(new BinaryTree.Node(4, "4"));
        tree.add(new BinaryTree.Node(2, "2"));
        tree.add(new BinaryTree.Node(6, "6"));
        tree.add(new BinaryTree.Node(3, "3"));
        tree.add(new BinaryTree.Node(5, "5"));
        tree.add(new BinaryTree.Node(1, "1"));
        tree.add(new BinaryTree.Node(7, "7"));
        tree.printPretty();
        tree.midThread();
        tree.midOrder();
    }

    @Test
    public void postThread() {
        ThreadBinaryTree tree = new ThreadBinaryTree();
        tree.add(new BinaryTree.Node(4, "4"));
        tree.add(new BinaryTree.Node(2, "2"));
        tree.add(new BinaryTree.Node(6, "6"));
        tree.add(new BinaryTree.Node(3, "3"));
        tree.add(new BinaryTree.Node(5, "5"));
        tree.add(new BinaryTree.Node(1, "1"));
        tree.add(new BinaryTree.Node(7, "7"));
        tree.printPretty();
        tree.postThread();
        tree.midOrder();
    }

    @Test
    public void preThreadLookup() {
        ThreadBinaryTree tree = new ThreadBinaryTree();
        tree.add(new BinaryTree.Node(4, "4"));
        tree.add(new BinaryTree.Node(2, "2"));
        tree.add(new BinaryTree.Node(6, "6"));
        tree.add(new BinaryTree.Node(3, "3"));
        tree.add(new BinaryTree.Node(5, "5"));
        tree.add(new BinaryTree.Node(1, "1"));
        tree.add(new BinaryTree.Node(7, "7"));
        tree.printPretty();
        tree.preThread();
        tree.preThreadLookup();
    }

    @Test
    public void midThreadLookup() {
        ThreadBinaryTree tree = new ThreadBinaryTree();
        tree.add(new BinaryTree.Node(4, "4"));
        tree.add(new BinaryTree.Node(2, "2"));
        tree.add(new BinaryTree.Node(6, "6"));
        tree.add(new BinaryTree.Node(3, "3"));
        tree.add(new BinaryTree.Node(5, "5"));
        tree.add(new BinaryTree.Node(1, "1"));
        tree.add(new BinaryTree.Node(7, "7"));
        tree.printPretty();
        tree.midThread();
        tree.midThreadLookup();
    }

    @Test
    public void postThreadLookup() {
        ThreadBinaryTree tree = new ThreadBinaryTree();
        tree.add(new BinaryTree.Node(4, "4"));
        tree.add(new BinaryTree.Node(2, "2"));
        tree.add(new BinaryTree.Node(6, "6"));
        tree.add(new BinaryTree.Node(3, "3"));
        tree.add(new BinaryTree.Node(5, "5"));
        tree.add(new BinaryTree.Node(1, "1"));
        tree.add(new BinaryTree.Node(7, "7"));
        tree.printPretty();
        tree.postThread();
        tree.postOrder();
//        tree.postThreadLookup();
    }

}
