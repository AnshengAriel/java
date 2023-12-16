package com.ariel.java.base.concurrent.thread;

import java.util.concurrent.locks.StampedLock;

public class Counter1 {

    private double x;
    private double y;
    private final StampedLock lock = new StampedLock();

    public void update(double x, double y) {
        long l = lock.writeLock();
        try {
            this.x = x;
            this.y = y;
        } finally {
            lock.unlockWrite(l);
        }
    }

    public double getXY() {
        long l = lock.tryOptimisticRead();
        double x1 = x;
        double y1 = y;
        if (!lock.validate(l)) {
            long l1 = lock.readLock();
            try {
                return x1 * y1;
            } finally {
                lock.unlockRead(l1);
            }
        }
        return x * y;
    }
}
