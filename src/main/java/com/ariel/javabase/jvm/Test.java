package com.ariel.javabase.jvm;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class Test {
    private static long MAX = Integer.MAX_VALUE / 50L;
    public static void main(String[] args) throws Exception {
        long sum = 0;
        int nThreads = Integer.parseInt(args[0]);
        int times = Integer.parseInt(args[1]);

        ExecutorService service = Executors.newFixedThreadPool(nThreads, new ThreadFactory() {
            private AtomicLong num = new AtomicLong(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "thread-mxsm-" + num.getAndIncrement());
                thread.setDaemon(false);
                return thread;
            }
        });
        for (int i = 0; i < times; ++i) {
            sum += extracted(nThreads,service);
        }
        System.out.println("线程数量："+nThreads+"  "+times+"次执行的平均时间："+(sum / times)+"ms");
        service.shutdown();
    }

    private static long extracted(int nThreads,ExecutorService service) throws InterruptedException {
        AtomicLong start = new AtomicLong(0);

        CountDownLatch countDownLatch = new CountDownLatch(1);
        CyclicBarrier barrier = new CyclicBarrier(nThreads);

        for (int i = 0; i < nThreads; ++i) {
            service.submit(new Runnable() {
                private volatile long current = 0;
                @Override
                public void run() {
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    do {
                        for (int i = 0; i < 200000; ++i) {
                        }
                        current = start.incrementAndGet();
                    } while (current < MAX);
                    countDownLatch.countDown();
                }
            });
        }
        long current = System.currentTimeMillis();
        countDownLatch.await();
        long time = System.currentTimeMillis() - current;
        return time;
    }

}
