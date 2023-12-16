package com.ariel.java.base.jvm.space;

public class AtomicSwap {

    public static void main(String[] args) throws InterruptedException {
        tryEscape();
        Thread.sleep(10000000);
    }

    public static void tryEscape() {
        A a = new A(1, "abc");
        for (int i = 0; i < 100000; i++) {
            a = new A(i, "vnds");
        }
        System.out.println(a.i + ":" + a.j);
    }

    public static class A {
        int i;
        String j;
        public A(int i, String j) {
            this.i = i;
            this.j = j;
        }
    }
}
