package com.ariel.javabase.jvm.init;

/**
 * static代码块只能被执行一次，在多线程初始化的情况下是会加锁的
 * 因此static代码块的内容要注意性能
 */
public class StaticInitOnce {

    public static void main(String[] args) {
        Runnable r = () -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + "开始执行");
            DeadThread deadThread = new DeadThread();
            System.out.println(name + "执行结束");
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
    }

}

class DeadThread {
    static {
        System.out.println("开始初始化");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("初始化成功");
    }
}
