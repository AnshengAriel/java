package com.ariel.java.base.concurrent.executor;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction {

    final int n;
    public final int[] result;

    public SimpleRecursiveAction(int n, int[] result) {
        this.n = n;
        this.result = result;
    }

    @Override
    protected void compute() {
        if (n <= 1) {
            result[0] += n;
        }else {
            SimpleRecursiveAction a1 = new SimpleRecursiveAction(n - 1, result);
            SimpleRecursiveAction a2 = new SimpleRecursiveAction(n - 2, result);
            a1.fork();
            a2.fork();

        }
    }

}
