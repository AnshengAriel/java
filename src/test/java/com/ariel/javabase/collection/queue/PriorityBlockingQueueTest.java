package com.ariel.javabase.collection.queue;

import org.junit.Test;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTest {

    @Test
    public void testPriorityBlockingQueue() {
//        putAndTake(new PriorityBlockingQueue<>(100));
//        offerAndPoll(new PriorityBlockingQueue<>(100));
        PriorityBlockingQueue<Object> queue = new PriorityBlockingQueue<>(100);
        queue.put(1);
    }

}
