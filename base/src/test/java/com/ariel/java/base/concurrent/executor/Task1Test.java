package com.ariel.java.base.concurrent.executor;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Task1Test {

    @Test
    public void execute() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            service.execute(new Task1("task-" + i));
        }
        service.awaitTermination(5, TimeUnit.SECONDS);
    }

    @Test
    public void execute1() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 4; i++) {
            service.execute(new Task1("task-" + i));
        }
        service.awaitTermination(10, TimeUnit.SECONDS);
    }
}
