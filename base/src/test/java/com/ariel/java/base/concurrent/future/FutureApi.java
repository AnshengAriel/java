package com.ariel.java.base.concurrent.future;

import com.ariel.javabase.util.OU;
import org.junit.Test;

import java.util.concurrent.*;

public class FutureApi {

    @Test
    public void testFutureTask() throws ExecutionException, InterruptedException {
        ExecutorService pool = OU.obs(Executors.newFixedThreadPool(10), true);
        Callable<String> task = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(500);
                return "success";
            }
        };

        Future<String> future1 = OU.apd(pool.submit(task), "submit", true);;
        Future<String> future2 = pool.submit(task);
        Future<String> future3 = pool.submit(task);

        System.out.println("future1.get() = " + future1.get());
        System.out.println("future2.get() = " + future2.get());
        System.out.println("future3.get() = " + future3.get());
    }

}
