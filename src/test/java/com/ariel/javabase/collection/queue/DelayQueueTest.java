package com.ariel.javabase.collection.queue;

import com.ariel.javabase.collection.DelayedOrder;
import org.junit.Test;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

    @Test
    public void t() throws InterruptedException {
        DelayQueue<DelayedOrder> queue = new DelayQueue<>();
        queue.add(new DelayedOrder(1, System.currentTimeMillis() + 5000));
        queue.add(new DelayedOrder(2, System.currentTimeMillis() + 8000));

        while (true) {
            DelayedOrder order = queue.poll(10, TimeUnit.SECONDS);
            if (order == null) {
                break;
            }
            System.out.println(order);
        }
    }
}
