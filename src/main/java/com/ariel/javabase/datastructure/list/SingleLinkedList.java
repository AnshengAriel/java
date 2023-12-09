package com.ariel.javabase.datastructure.list;

/**
 * 带头结点的单链表
 */
public class SingleLinkedList {

    private Node head = new Node(0, "");

    public boolean add(Node node) {
        if (node == null) {
            return false;
        }
        Node temp = head;
        while (true) {
            if (temp.next != null) {
                temp = temp.next;
            }else {
                temp.next = node;
                break;
            }
        }
        return true;
    }

    public boolean addFirst(Node node) {
        if (node == null) {
            return false;
        }
        if (head.next == null) {
            node.next = null;
            head.next = node;
        }else {
            node.next = head.next;
            head.next = node;
        }
        return true;
    }

    public boolean addByOrder(Node node) {
        if (node == null) {
            return false;
        }
        Node temp = head;
        while (true) {
            if (temp.next != null) {
                if (temp.next.id > node.id) {
                    node.next = temp.next;
                    temp.next = node;
                    return true;
                }
                temp = temp.next;
            }else {
                temp.next = node;
                break;
            }
        }
        return true;
    }

    public boolean update(Node node) {
        if (node == null) {
            return false;
        }
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.id == node.id) {
                node.next = temp.next.next;
                temp.next = node;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public boolean delete(int id) {
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.id == id) {
                temp.next = temp.next.next;
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    public void list() {
        Node temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public int size() {
        int size = 0;
        Node temp = head.next;
        while (temp != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }

    public Node head() {
        return head;
    }

    /**
     * 获取倒数第n个节点，还可以快慢指针，递归计数器等方法解决
     * @param n 倒数
     * @return 节点
     */
    public Node getByLast(Integer n) {
        if (n == null || n <= 0) {
            return null;
        }
        int size = size();
        if (n > size) {
            return null;
        }
        Node temp = head;
        for (int i = 0; i < size - n; i++) {
            temp = temp.next;
        }
        return temp.next;
    }

    /**
     * 链表反转，也可以用递归，三个指针解决
     */
    public void reverse() {
        SingleLinkedList temList = new SingleLinkedList();
        Node temp = head.next;
        while (temp != null) {
            Node next = temp.next;
            temList.addFirst(temp);
            temp = next;
        }
        head.next = temList.head().next;
    }

    /**
     * 使用三指针反转
     */
    public void reverse1() {
        Node temp1;
        Node temp2;
        Node temp3;
        if ((temp1 = head.next) == null) {
            return;
        }
        if ((temp2 = head.next.next) == null) {
            return;
        }
        if ((temp3 = head.next.next.next) == null) {
            // 第二个有效节点指向第一个
            head.next.next.next = head.next.next;
            // 头节点指向第二个有效节点
            head.next = head.next.next;
            // 第一个节点指向空
            head.next.next.next = null;
        }
        temp1.next = null;
        while (temp2 != null) {
            temp2.next = temp1;
            temp1 = temp2;
            temp2 = temp3;
            if (temp3 != null) {
                temp3 = temp3.next;
            }
        }
        head.next = temp1;
    }

    /**
     * 使用递归
     */
    public void reverse2() {
        if (head.next != null) {
            // 第一个有效节点指向空
            nextReverse(head.next).next = null;
        }
    }

    private Node nextReverse(Node node) {
        if (node == null) {
            return null;
        }
        // true=最后一个节点是node，此时需要让头节点指向最后一个节点
        if (nextReverse(node.next) == null) {
            head.next = node;
        }else {
            node.next.next = node;
        }
        return node;
    }
}
