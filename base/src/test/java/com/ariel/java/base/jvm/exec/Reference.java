package com.ariel.java.base.jvm.exec;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * -Xms30m -Xmx30m -XX:+PrintGCDetails
 */
public class Reference {

    public static ReferenceQueue<MyData> queue = new ReferenceQueue<>();

    public static void main(String[] args) throws InterruptedException {

    }

    public static void softRef() {
        SoftReference<MyData> data = new SoftReference<>(new MyData("hello"));
        try {
            byte[] bytes = new byte[1024 * 1024 * 20];
        } catch (OutOfMemoryError e) {
            throw new RuntimeException(e);
        } finally {
            // Allocation Failure的情况下才会释放软引用
            System.out.println(data.get());
        }
    }

    public static void weakRef() {
        // 弱引用，GC即释放
        WeakReference<MyData> data = new WeakReference<>(new MyData("hello"));
        System.gc();
        System.out.println(data.get());

        WeakHashMap<String, String> map = new WeakHashMap<>();
    }

    public static void phantomRef() throws InterruptedException {
        // 构建虚引用需要提供一个通知队列，当虚引用所指对象被释放后，jvm回收线程会将此虚引用添加到通知队列
        PhantomReference<MyData> data = new PhantomReference<MyData>(new MyData("hello"), queue);
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    if (queue.remove() != null) {
                        System.out.println("对象被释放了");
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
        System.gc();
        Thread.sleep(100);
    }

    public static class MyData {
        String name;
        public MyData(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

}
