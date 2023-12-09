package com.ariel.javabase.concurrent.thread;

import lombok.Data;

import java.util.concurrent.locks.ReentrantReadWriteLock;

@Data
public class Counter {

    private final int[] array;

    private final ReentrantReadWriteLock readWriteLock;

    public Counter(int slots) {
        this.array = new int[10];
        this.readWriteLock = new ReentrantReadWriteLock();
    }

    public void add(int index) {
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        try {
            writeLock.lock();
            array[index]++;
        } finally {
            writeLock.unlock();
        }
    }

    public int get(int index) {
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        try {
            readLock.lock();
            return array[index];
        } finally {
            readLock.unlock();
        }
    }

}
