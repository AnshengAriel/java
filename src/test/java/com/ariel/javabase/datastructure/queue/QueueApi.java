package com.ariel.javabase.datastructure.queue;

import org.junit.Test;

public class QueueApi {

    @Test
    public void test() {
        int size = 10;
        Queue queue = new Queue(size);
        for (int i = 0; i < size; i++) {
            System.out.print(i);
            queue.push(i);
        }
        System.out.println(" 输入");
        for (int i = 0; i < size; i++) {
            System.out.print(queue.pull());
        }

        // 测试超出队列的情况
        System.out.println();
        for (int i = size - 1; i >= 0; i--) {
            System.out.print(i);
            queue.push(i);
        }
        System.out.println(" 输入");
        for (int i = 0; i < size; i++) {
            System.out.print(queue.pull());
        }
    }
}
