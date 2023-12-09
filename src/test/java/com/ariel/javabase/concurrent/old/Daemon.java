package com.ariel.javabase.concurrent.old;

/**
 * 守护线程
 */
public class Daemon {

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> {
            // 无限循环，作为守护线程会随着工作线程结束而自动结束
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    System.out.printf("thread1 running[%s]%n", System.currentTimeMillis() - l);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    System.out.printf("thread2 running[%s]%n", System.currentTimeMillis() - l);
                }
            }
            System.out.println("thread2 terminal");
        });

        thread2.start();
        // 标记为守护线程
        thread1.setDaemon(true);
        // 启动thread1作为守护线程，当thread2和main（所有工作线程）运行结束了，thread1将会自动结束
        thread1.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("main terminal");
    }
}
