package com.ariel.javabase.concurrent.aqs;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    @Test
    public void lock() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        System.out.println("hello");
        lock.unlock();
    }

    @Test
    public void lock1() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        Condition condition = lock.newCondition();
        condition.await();
        condition.signal();
    }

}
