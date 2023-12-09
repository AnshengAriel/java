package com.ariel.javabase.concurrent.old;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * [线程池](project\_20230525215602\src\test\java\com\ariel\thread\_20230527225104.java)
 * <a href='project\_20230525215602\src\test\java\com\ariel\thread\_20230527225104.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230527225104 {

    @Test
    public void testA() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                System.out.printf("Thread[%s]%n", Thread.currentThread().getName());
            });
        }
        System.out.println("hello");
    }

    @Test
    public void testB() throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);
        for (int i = 0; i < 11; i++) {
            queue.offer(i, 10, TimeUnit.SECONDS);
        }
        System.out.println("queue.poll(10, TimeUnit.SECONDS) = " + queue.poll(10, TimeUnit.SECONDS));
        System.out.println("queue.take() = " + queue.take());
        System.out.println("queue.poll() = " + queue.poll());
    }
}
