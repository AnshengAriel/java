package com.ariel.javabase.concurrent.executor;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulePoolTest {

    @Test
    public void schedule() throws InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        pool.schedule(() -> System.out.println("Hello 1"), 10, TimeUnit.SECONDS);
        pool.schedule(() -> System.out.println("Hello 2"), 5, TimeUnit.SECONDS);
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }

    @Test
    public void test() throws InterruptedException {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        pool.schedule(() -> System.out.println("Hello 1"), 10, TimeUnit.SECONDS);
        pool.schedule(() -> {throw new RuntimeException("Hello 2");}, 5, TimeUnit.SECONDS);
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }

}
