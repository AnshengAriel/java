package com.ariel.java.base.concurrent.aqs;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockTest {

    @Test
    public void t() {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        lock.readLock().lock();
        System.out.println("Hello");
        lock.readLock().unlock();
    }
}
