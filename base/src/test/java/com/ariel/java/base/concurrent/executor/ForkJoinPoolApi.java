package com.ariel.java.base.concurrent.executor;

import com.ariel.javabase.concurrent.executor.SimpleRecursiveAction;
import com.ariel.javabase.concurrent.executor.SimpleRecursiveTask;
import com.ariel.javabase.util.OU;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinPoolApi {

    @Test
    public void invoke() {
        // 处理回调型任务
        SimpleRecursiveTask t = new SimpleRecursiveTask(10);
        ForkJoinPool p = OU.obs(new ForkJoinPool(), false);
        Integer result = OU.apd(p.invoke(t), "p.invoke", true);
        System.out.println("result = " + result);
    }

    @Test
    public void submitTask() throws ExecutionException, InterruptedException {
        // 处理回调型任务
        SimpleRecursiveTask t = new SimpleRecursiveTask(10);
        ForkJoinPool p = new ForkJoinPool();
        ForkJoinTask<Integer> task = p.submit(t);
        System.out.println("result = " + task.get());
    }

    @Test
    public void submitAction() throws ExecutionException, InterruptedException {
        SimpleRecursiveAction t = new SimpleRecursiveAction(10, new int[]{0});
        ForkJoinPool p = new ForkJoinPool();
        p.submit(t).get();
        Thread.sleep(1000);
        System.out.println("result = " + t.result[0]);
    }

}
