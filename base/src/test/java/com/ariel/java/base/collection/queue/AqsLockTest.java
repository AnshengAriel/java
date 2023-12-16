package com.ariel.java.base.collection.queue;

import com.ariel.javabase.collection.AqsLock;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class AqsLockTest {

    int i = 100;

    @Test
    public void lock() throws InterruptedException, NoSuchFieldException, IllegalAccessException {
        AqsLock lock = new AqsLock();
        exe(lock);
        AqsLock.Sync sync = lock.getAqs();
        Field head = AbstractQueuedSynchronizer.class.getDeclaredField("head");
        head.setAccessible(true);
        Field tail = AbstractQueuedSynchronizer.class.getDeclaredField("tail");
        tail.setAccessible(true);
        Object headValue = head.get(sync);
        Object tailValue = tail.get(sync);

        Thread.sleep(1000000);
    }

    public void exe(AqsLock lock) {
        Runnable r = () -> {
            for (int j = 0; j < 10; j++) {
                lock.lock();
                i--;
                System.out.printf("%s: %d%n", Thread.currentThread().getName(), i);
                lock.unlock();
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        for (int j = 0; j < 10; j++) {
            new Thread(r).start();
        }
    }

    public static void main(String[] args) {
        Field[] fields = AbstractQueuedSynchronizer.class.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }

}
