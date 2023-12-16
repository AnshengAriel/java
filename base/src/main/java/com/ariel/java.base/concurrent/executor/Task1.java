package com.ariel.java.base.concurrent.executor;

import java.util.concurrent.TimeUnit;

public class Task1 implements Runnable {

    private final String name;

    public Task1(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("Start task: " + name);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Task end: " + name);
    }
}
