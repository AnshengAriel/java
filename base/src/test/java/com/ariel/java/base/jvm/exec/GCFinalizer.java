package com.ariel.java.base.jvm.exec;

public class GCFinalizer {

    public static void main(String[] args) {
        new GCFinalizer();
        System.gc();
        // 强制调用finalize()方法
        System.runFinalization();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("调用了finalize()");
    }
}
