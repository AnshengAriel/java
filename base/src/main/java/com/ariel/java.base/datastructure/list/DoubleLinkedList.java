package com.ariel.java.base.datastructure.list;

/**
 * 带头结点的单链表
 */
public class DoubleLinkedList {

    private DoubleNode head = new DoubleNode(0, "");

    public boolean add(DoubleNode node) {
        if (node == null) {
            return false;
        }
        DoubleNode temp = head;
        while (true) {
            if (temp.next != null) {
                temp = temp.next;
            }else {
                temp.next = node;
                node.pre = temp;
                break;
            }
        }
        return true;
    }

    public boolean addFirst(DoubleNode node) {
        if (node == null) {
            return false;
        }
        if (head.next == null) {
            node.next = null;
            head.next = node;
            node.pre = head;
        }else {
            node.next = head.next;
            head.next = node;
            node.pre = head;
        }
        return true;
    }

    public boolean addByOrder(DoubleNode node) {
        if (node == null) {
            return false;
        }
        DoubleNode temp = head;
        while (true) {
            if (temp.next != null) {
                if (temp.next.id > node.id) {
                    node.next = temp.next;
                    temp.next = node;
                    node.pre = temp;
                    return true;
                }
                temp = temp.next;
            }else {
                temp.next = node;
                node.pre = temp;
                break;
            }
        }
        return true;
    }

    public boolean update(DoubleNode node) {
        if (node == null) {
            return false;
        }
        DoubleNode temp = head;
        while (temp.next != null) {
            if (temp.next.id == node.id) {
                node.next = temp.next.next;
                temp.next = node;
                node.pre = temp;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public boolean delete(int id) {
        DoubleNode temp = head;
        while (temp.next != null) {
            if (temp.next.id == id) {
                temp.next = temp.next.next;
                temp.next.pre = temp;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void list() {
        DoubleNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public int size() {
        int size = 0;
        DoubleNode temp = head.next;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }

    public DoubleNode head() {
        return head;
    }

    /**
     * 获取倒数第n个节点，还可以快慢指针，递归计数器等方法解决
     * @param n 倒数
     * @return 节点
     */
    public DoubleNode getByLast(Integer n) {
        if (n == null || n <= 0) {
            return null;
        }
        int size = size();
        if (n > size) {
            return null;
        }
        DoubleNode temp = head;
        for (int i = 0; i < size - n; i++) {
            temp = temp.next;
        }
        return temp.next;
    }

    /**
     * 链表反转，用头插法
     */
    public void reverse() {
        DoubleLinkedList temList = new DoubleLinkedList();
        DoubleNode temp = head.next;
        while (temp != null) {
            DoubleNode next = temp.next;
            temList.addFirst(temp);
            temp = next;
        }
        head.next = temList.head().next;
    }

}
