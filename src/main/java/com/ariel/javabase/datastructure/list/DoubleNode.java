package com.ariel.javabase.datastructure.list;

public class DoubleNode {

    int id;

    String name;

    DoubleNode pre;

    DoubleNode next;

    public DoubleNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Node{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
