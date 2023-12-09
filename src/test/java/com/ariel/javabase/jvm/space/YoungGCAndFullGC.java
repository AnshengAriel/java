package com.ariel.javabase.jvm.space;

import java.util.ArrayList;

public class YoungGCAndFullGC {

    /**
     * -Xms60m -Xmx60m -XX:+PrintGCDetails
     * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
     */
    public static void main(String[] args) {
        int size = 1024 * 1024;
        ArrayList<byte[]> list = new ArrayList<>();
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            list.add(new byte[size]);
        }
    }

}
