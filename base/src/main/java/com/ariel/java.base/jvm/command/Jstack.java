package com.ariel.java.base.jvm.command;

public class Jstack {

    public static void main(String[] args) {
        String lock1 = "lock1";
        String lock2 = "lock2";
        new Thread(getRunnable(lock1, lock2)).start();
        new Thread(getRunnable(lock2, lock1)).start();


    }

    public static Runnable getRunnable(String lock1, String lock2) {
        return new Runnable() {
            @Override
            public void run() {
                synchronized (lock1) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (lock2) {
                        System.out.println("...");
                    }
                }
            }
        };
    }
}
