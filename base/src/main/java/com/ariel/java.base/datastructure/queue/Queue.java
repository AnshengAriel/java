package com.ariel.java.base.datastructure.queue;

public class Queue {
    /**
     * 数组长度
     */
    private int size;
    /**
     * 指向队头
     */
    private int front;
    /**
     * 指向队尾后一个位置
     */
    private int rear;
    /**
     * 存储所有元素的数组
     */
    private int[] array;

    public Queue(int size) {
        this.size = size;
        this.array = new int[size];
    }

    public boolean isEmpty() {
        // 想象一个针管，针头位置是front，活塞位置是rear，里面的水是数据，因此判空要相等，同时活塞也要占据一个位置。
        return front == rear;
    }

    public boolean isFull() {
        return rear - front == size;
    }

    public boolean push(int e) {
        if (isFull()) {
            return false;
        }
        array[rear%size] = e;
        rear++;
        return true;
    }

    public Integer pull() {
        if (isEmpty()) {
            return null;
        }
        int v = array[front++];
        // true=队头会超出数组容量，因此重置为0
        if (front == size) {
            front = 0;
            rear -= size;
        }
        return v;
    }

}
