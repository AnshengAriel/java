package com.ariel.java.base.datastructure.list;

/**
 * 单向环形链表
 */
public class CircleSingleLinkedList {

    private CircleNode curr;

    public boolean add(CircleNode node) {
        if (node == null) {
            return false;
        }
        if (curr == null) {
            node.next = node;
        }else {
            node.next = curr.next;
            curr.next = node;
        }
        curr = node;
        return true;
    }

    public void setCurr(Integer id) {
        if (curr == null) {
            return;
        }
        for (CircleNode temp = curr.next; temp != curr; temp = temp.next) {
            if (temp.id == id) {
                curr = temp;
                break;
            }
        }
    }


    @Override
    public String toString() {
        if (curr == null) {
            return "null";
        }
        StringBuilder builder = new StringBuilder(curr.toString()).append("\n");
        for (CircleNode temp = curr.next; temp != curr; temp = temp.next) {
            builder.append(temp).append("\n");
        }
        return builder.toString();
    }

    public void printOut(Integer start, Integer step) {
        if (step == null || step <= 0) {
            return;
        }
        setCurr(start);
        System.out.println(this);
        if (step == 1) {
            System.out.println(this);
            return;
        }
        curr = curr.next(step - 2);
        while (true) {
            CircleNode temp = curr.next;
            // true=环中还剩下最后一个节点
            if (temp == curr) {
                System.out.println(temp);
                break;
            }
            System.out.println(temp);
            curr.next = temp.next;
            curr = curr.next(step - 1);
        }

    }

}
