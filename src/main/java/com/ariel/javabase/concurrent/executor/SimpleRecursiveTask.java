package com.ariel.javabase.concurrent.executor;

import java.util.concurrent.RecursiveTask;

/**
 * 带返回值的任务，类似Callable
 */
public class SimpleRecursiveTask extends RecursiveTask<Integer> {

    final int n;

    public SimpleRecursiveTask(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        SimpleRecursiveTask t1 = new SimpleRecursiveTask(n - 1);
        SimpleRecursiveTask t2 = new SimpleRecursiveTask(n - 2);
        t1.fork();
        t2.fork();
        Integer i = t1.join();
        System.out.println(Thread.currentThread().getName() + ": " + n);
        Integer j = t2.join();
        return i + j;
    }
}
