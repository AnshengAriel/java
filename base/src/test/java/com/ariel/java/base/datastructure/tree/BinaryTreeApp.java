package com.ariel.java.base.datastructure.tree;

import org.junit.Test;

public class BinaryTreeApp {

    @Test
    public void preOrder() {
        BinaryTree tree = new BinaryTree();
        for (int i = 0, temp; i < 100; i++) {
            temp = (int)(Math.random() * 100);
            tree.add(new BinaryTree.Node(temp, "Hello " + i));
        }
        tree.printPretty();
        tree.preOrder();
    }

    @Test
    public void midOrder() {
        BinaryTree tree = new BinaryTree();
        for (int i = 0, temp; i < 100; i++) {
            temp = (int)(Math.random() * 100);
            tree.add(new BinaryTree.Node(temp, "Hello " + i));
        }
        tree.midOrder();
    }

    @Test
    public void postOrder() {
        BinaryTree tree = new BinaryTree();
        for (int i = 0, temp; i < 100; i++) {
            temp = (int)(Math.random() * 100);
            tree.add(new BinaryTree.Node(temp, "Hello " + i));
        }
        tree.postOrder();
    }

    @Test
    public void preLookup() {
        BinaryTree tree = new BinaryTree();
        for (int i = 0, temp; i < 100; i++) {
            temp = (int)(Math.random() * 100);
            tree.add(new BinaryTree.Node(temp, "Hello " + i));
        }
        BinaryTree.Node node = tree.preLookup(55);
        System.out.println("node = " + node);
    }

    @Test
    public void midLookup() {
        BinaryTree tree = new BinaryTree();
        for (int i = 0, temp; i < 100; i++) {
            temp = (int)(Math.random() * 100);
            tree.add(new BinaryTree.Node(temp, "Hello " + i));
        }
        BinaryTree.Node node = tree.midLookup(55);
        System.out.println("node = " + node);
    }

    @Test
    public void postLookup() {
        BinaryTree tree = new BinaryTree();
        for (int i = 0, temp; i < 100; i++) {
            temp = (int)(Math.random() * 100);
            tree.add(new BinaryTree.Node(temp, "Hello " + i));
        }
        BinaryTree.Node node = tree.postLookup(55);
        System.out.println("node = " + node);
    }

    @Test
    public void delete() {
        BinaryTree tree = new BinaryTree();
        for (int i = 0, temp; i < 100; i++) {
            temp = (int)(Math.random() * 100);
            tree.add(new BinaryTree.Node(temp, "Hello " + i));
        }
        BinaryTree.Node node = tree.preLookup(55);
        System.out.println("node = " + node);
        tree.delete(55);
        node = tree.preLookup(55);
        System.out.println("node = " + node);
        tree.printPretty();
    }

}
