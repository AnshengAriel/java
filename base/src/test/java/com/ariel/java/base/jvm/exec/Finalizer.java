package com.ariel.java.base.jvm.exec;

public class Finalizer {

    private static Finalizer obj;

    @Override
    protected void finalize() throws Throwable {
        // finalize由GC线程调用，只会调用一次
        System.out.println("开始执行finalize()");
        obj = this;
    }

    public static void main(String[] args) throws InterruptedException {
        obj = new Finalizer();
        obj = null;

        // gc线程优先级很低，使主线程延迟2秒
        System.gc();
        Thread.sleep(2000);

        if (obj == null) {
            System.out.println("obj直接死了");
        }else {
            System.out.println("obj复活了");
        }

        obj = null;

        System.gc();
        Thread.sleep(2000);

        if (obj == null) {
            System.out.println("obj又死了");
        }else {
            System.out.println("obj没有复活");
        }

    }

}
