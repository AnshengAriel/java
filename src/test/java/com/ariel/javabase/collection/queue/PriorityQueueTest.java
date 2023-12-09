package com.ariel.javabase.collection.queue;

import org.junit.Test;

import java.util.PriorityQueue;

public class PriorityQueueTest {

    @Test
    public void testPriorityQueue() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(1);
        System.out.println("queue.poll() = " + queue.poll());
    }

}
