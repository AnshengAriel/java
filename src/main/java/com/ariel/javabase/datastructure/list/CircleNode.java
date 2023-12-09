package com.ariel.javabase.datastructure.list;

public class CircleNode {

    Integer id;

    CircleNode next;

    public CircleNode(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CircleNode{" +
                "id=" + id +
                '}';
    }

    public CircleNode next(Integer step) {
        CircleNode temp = this;
        for (int i = 0; i < step; i++) {
            temp = temp.next;
        }
        return temp;
    }
}
