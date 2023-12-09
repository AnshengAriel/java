package com.ariel.javabase.collection.queue;

import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueTest {

    @Test
    public void testConcurrentLinkedQueue() {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.add(1);
        System.out.println("queue.poll() = " + queue.poll());
    }

}
