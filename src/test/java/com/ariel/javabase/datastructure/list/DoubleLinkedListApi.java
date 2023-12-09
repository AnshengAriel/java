package com.ariel.javabase.datastructure.list;

import org.junit.Test;

public class DoubleLinkedListApi {

    @Test
    public void add() {
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(new DoubleNode(3, "name3"));
        list.add(new DoubleNode(2, "name1"));
        list.add(new DoubleNode(4, "name4"));
        list.list();
    }

    @Test
    public void addByOrder() {
        DoubleLinkedList list = new DoubleLinkedList();
        list.addByOrder(new DoubleNode(4, "name4"));
        list.addByOrder(new DoubleNode(3, "name3"));
        list.addByOrder(new DoubleNode(2, "name1"));
        list.list();
    }

    @Test
    public void update() {
        DoubleLinkedList list = new DoubleLinkedList();
        list.addByOrder(new DoubleNode(4, "name4"));
        list.addByOrder(new DoubleNode(1, "name3"));
        list.addByOrder(new DoubleNode(2, "name1"));
        list.list();
        list.update(new DoubleNode(2, "update"));
        list.list();
    }

    @Test
    public void delete() {
        DoubleLinkedList list = new DoubleLinkedList();
        list.addByOrder(new DoubleNode(4, "name4"));
        list.addByOrder(new DoubleNode(1, "name3"));
        list.addByOrder(new DoubleNode(2, "name1"));
        list.list();
        list.delete(2);
        list.list();
    }

    @Test
    public void size() {
        DoubleLinkedList list = new DoubleLinkedList();
        list.addByOrder(new DoubleNode(4, "name4"));
        list.addByOrder(new DoubleNode(1, "name3"));
        list.addByOrder(new DoubleNode(2, "name1"));
        list.list();
        System.out.println("list.size() = " + list.size());
    }

    @Test
    public void getByLast() {
        DoubleLinkedList list = new DoubleLinkedList();
        list.addByOrder(new DoubleNode(4, "name4"));
        list.addByOrder(new DoubleNode(1, "name3"));
        list.addByOrder(new DoubleNode(2, "name1"));
        list.list();
        System.out.println("list.getByLast(2) = " + list.getByLast(2));
    }

    @Test
    public void reverse() {
        DoubleLinkedList list = new DoubleLinkedList();
        list.addByOrder(new DoubleNode(4, "name4"));
        list.addByOrder(new DoubleNode(1, "name3"));
        list.addByOrder(new DoubleNode(2, "name1"));
        list.list();
        list.reverse();
        list.list();

    }

}
