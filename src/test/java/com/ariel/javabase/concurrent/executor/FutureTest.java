package com.ariel.javabase.concurrent.executor;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTest {

    @Test
    public void get() throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(() -> "hello");
        task.run();
        String s = task.get();
        System.out.println(s);
    }

}
