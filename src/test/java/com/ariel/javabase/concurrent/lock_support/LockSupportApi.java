package com.ariel.javabase.concurrent.lock_support;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

public class LockSupportApi {

    @Test
    public void parkNanos() throws InterruptedException {
        Thread parkNanosThread = new Thread(()-> LockSupport.parkNanos(3000*4000*1000L));
        Thread parkThread = new Thread(()-> LockSupport.park());

        parkNanosThread.start();
        parkThread.start();

        Thread.sleep(1000L);

        // LockSupport.parkNanos(3000*4000*1000L)会让线程进入WAITING，永久处于休眠状态
        System.out.println("parkNanosThread.getState() = " + parkNanosThread.getState());

        // LockSupport.park()会让线程进入TIMED_WAITING，在一段时间后自动唤醒
        System.out.println("parkThread.getState() = " + parkThread.getState());
    }

}
