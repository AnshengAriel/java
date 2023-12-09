package com.ariel.javabase.datastructure.tree;

import org.junit.Test;

public class HuffmanTreeApp {

    @Test
    public void build() {
        int[] ints = {8, 9, 34, 1, 6, 7, 3, 8};
        HuffmanTree tree = new HuffmanTree();
        tree.build(ints);
        tree.printPretty();
    }

    @Test
    public void codec() {
        HuffmanTree tree = new HuffmanTree();
        byte[] codec = tree.encode("hello world ! hello world ! hello world ! hello world ! hello world ! hello world ! hello world !");
        tree.printPretty();
        String decode = new String(tree.decode(codec));
        System.out.println("decode = " + decode);
    }

    @Test
    public void zip() {
        HuffmanTree tree = new HuffmanTree();
        String source = "D:\\ariel\\project\\data-structure\\md\\.assets\\20230908193057\\";
        tree.zip(source + "image-20230911101224692.png", source + "a.zip");
    }

    @Test
    public void unzip() {
        HuffmanTree tree = new HuffmanTree();
        String source = "D:\\ariel\\project\\data-structure\\md\\.assets\\20230908193057\\";
        tree.upzip( source + "a.zip", source + "b.png");
    }
}
