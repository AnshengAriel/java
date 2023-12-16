package com.ariel.java.base.jvm.exec;

/**
 * -Xms300m -Xmx300m -XX:+PrintGCDetails
 */
public class GCLocalVar {

    public void gc1() {
        byte[] bytes = new byte[10 * 1024 * 1024];
        System.gc();
    }

    public void gc2() {
        byte[] bytes = new byte[10 * 1024 * 1024];
        bytes = null;
        System.gc();
    }

    public void gc3() {
        {
            byte[] bytes = new byte[10 * 1024 * 1024];
        }
        System.gc();
    }

    public void gc4() {
        {
            byte[] bytes = new byte[10 * 1024 * 1024];
        }
        int i = 0;
        System.gc();
    }

    public void gc5() {
        gc1();
        System.gc();
    }

    public void gc6() {
        if (true) {
            byte[] bytes = new byte[10 * 1024 * 1024];
        }
        System.gc();
    }

    public static void main(String[] args) {
        GCLocalVar gcLocalVar = new GCLocalVar();
        gcLocalVar.gc6();
    }

}
