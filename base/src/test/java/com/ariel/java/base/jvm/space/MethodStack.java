package com.ariel.java.base.jvm.space;

import org.junit.Test;

public class MethodStack {

    @Test
    public void stackOverFlowError() {
        stackOverFlowError();
    }

    @Test
    public void outOfMemoryError() {
        int[] ints = new int[10_0000_0000];
    }

    public static void main(String[] args) {
        method1();
    }

    public static void method1() {
        method2();
    }

    private static void method2() {
        System.out.println("method2");
        int i = 1/0;
    }
}
