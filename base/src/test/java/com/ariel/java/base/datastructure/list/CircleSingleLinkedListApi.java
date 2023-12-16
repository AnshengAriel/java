package com.ariel.java.base.datastructure.list;

import org.junit.Test;

public class CircleSingleLinkedListApi {

    @Test
    public void add() {
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        list.add(new CircleNode(2));
        list.add(new CircleNode(1));
        list.add(new CircleNode(5));
        list.add(new CircleNode(3));
        list.add(new CircleNode(4));
        System.out.println(list);
    }

    @Test
    public void printOut() {
        play(5, 1, 2);
    }

    /**
     * 约瑟夫问题
     * @param num 总人数
     * @param id 第一个人ID
     * @param step 报数
     */
    public void play(Integer num, Integer id, Integer step) {
        if (num == null || id == null || step == null) {
            return;
        }
        CircleSingleLinkedList list = new CircleSingleLinkedList();
        for (Integer i = 1; i <= num; i++) {
            list.add(new CircleNode(i));
        }
        list.printOut(id, step);
    }

}
