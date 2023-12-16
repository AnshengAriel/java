package com.ariel.java.base.concurrent.aqs;

import com.ariel.javabase.concurrent.lock.BoundedBuffer;
import org.junit.Test;

// TODO
public class ConditionTest {

    @Test
    public void put() throws InterruptedException {
        BoundedBuffer buffer = new BoundedBuffer();
        buffer.put(new Object());
        buffer.put(new Object());
        Object take = buffer.take();
        buffer.put(take);
        System.out.print("hello");
    }

}
