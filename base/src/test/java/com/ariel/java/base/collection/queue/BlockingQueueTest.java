package com.ariel.java.base.collection.queue;

import org.junit.Test;

import java.util.concurrent.*;

public class BlockingQueueTest {

    @Test
    public void testArrayBlockingQueue() {
        putAndTake(new ArrayBlockingQueue<>(100));
        offerAndPoll(new ArrayBlockingQueue<>(100));
    }

    @Test
    public void testLinkedBlockingQueue() {
        putAndTake(new LinkedBlockingQueue<>(100));
        offerAndPoll(new LinkedBlockingQueue<>(100));
    }

    public void putAndTake(BlockingQueue<Integer> blockingQueue) {
        System.out.println("---------------------------putAndTake------------------------------");
        final int count = blockingQueue.remainingCapacity();
        new Thread(() -> {
            try {
                for (int i = 0; i < count; i++) {
                    blockingQueue.put(i);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        try {
            for (int i = 0; i < count; i++) {
                System.out.println(blockingQueue.take());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void offerAndPoll(BlockingQueue<Integer> blockingQueue) {
        System.out.println("---------------------------poll faster------------------------------");
        final int count = blockingQueue.remainingCapacity();
        // 传入一个空队列，每一秒钟向队列添加一个元素，通过poll限时获取
        new Thread(() -> {
            try {
                for (int i = 0; i < count; i++) {
                    System.out.println(blockingQueue.poll(100, TimeUnit.MILLISECONDS));
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        for (int i = 0; i < count; i++) {
            try {
                blockingQueue.add(i);
                TimeUnit.MILLISECONDS.sleep(80L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // 生成一个满队列，每秒向队列中offer一个值，每秒从队列中poll一个值
        for (int i = 0; i < count; i++) {
            blockingQueue.add(i);
        }

        System.out.println("---------------------------offer faster------------------------------");
        new Thread(() -> {
            try {
                for (int i = 0; i < count; i++) {
                    blockingQueue.offer(i, 100, TimeUnit.MILLISECONDS);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        for (int i = 0; i < count; i++) {
            try {
                System.out.println(blockingQueue.poll());
                TimeUnit.MILLISECONDS.sleep(80L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
