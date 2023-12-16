package com.ariel.java.base.collection.queue;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.SynchronousQueue;

/**
 * 在 Java 的队列中有一个比较特殊的队列 SynchronousQueue，
 * 它的特别之处在于它内部没有容器，每次进行 put() 数据后（添加数据），
 * 必须等待另一个线程拿走数据后才可以再次添加数据，它的使用示例如下：
 */
public class SynchronousQueueTest {

    @Test
    public void t() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    queue.put(i);
                    System.out.println("enqueue: " + i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    System.out.println("dequeue: " + queue.take());
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        countDownLatch.await();
    }
}
