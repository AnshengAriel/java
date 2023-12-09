package com.ariel.javabase.datastructure.list;

import org.junit.Test;

public class SingleLinkedListApi {

    @Test
    public void add() {
        SingleLinkedList list = new SingleLinkedList();
        list.add(new Node(3, "name3"));
        list.add(new Node(2, "name1"));
        list.add(new Node(4, "name4"));
        list.list();
    }

    @Test
    public void addByOrder() {
        SingleLinkedList list = new SingleLinkedList();
        list.addByOrder(new Node(4, "name4"));
        list.addByOrder(new Node(3, "name3"));
        list.addByOrder(new Node(2, "name1"));
        list.list();
    }

    @Test
    public void update() {
        SingleLinkedList list = new SingleLinkedList();
        list.addByOrder(new Node(4, "name4"));
        list.addByOrder(new Node(1, "name3"));
        list.addByOrder(new Node(2, "name1"));
        list.list();
        list.update(new Node(2, "update"));
        list.list();
    }

    @Test
    public void delete() {
        SingleLinkedList list = new SingleLinkedList();
        list.addByOrder(new Node(4, "name4"));
        list.addByOrder(new Node(1, "name3"));
        list.addByOrder(new Node(2, "name1"));
        list.list();
        list.delete(2);
        list.list();
    }

    @Test
    public void size() {
        SingleLinkedList list = new SingleLinkedList();
        list.addByOrder(new Node(4, "name4"));
        list.addByOrder(new Node(1, "name3"));
        list.addByOrder(new Node(2, "name1"));
        list.list();
        System.out.println("list.size() = " + list.size());
    }

    @Test
    public void getByLast() {
        SingleLinkedList list = new SingleLinkedList();
        list.addByOrder(new Node(4, "name4"));
        list.addByOrder(new Node(1, "name3"));
        list.addByOrder(new Node(2, "name1"));
        list.list();
        System.out.println("list.getByLast(2) = " + list.getByLast(2));
    }

    @Test
    public void reverse() {
        SingleLinkedList list = new SingleLinkedList();
        list.addByOrder(new Node(4, "name4"));
        list.addByOrder(new Node(1, "name3"));
        list.addByOrder(new Node(2, "name1"));
        list.list();
        list.reverse2();
        list.list();

    }

}
