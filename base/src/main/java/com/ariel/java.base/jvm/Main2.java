package com.ariel.java.base.jvm;

public class Main2 {

    public int i = 0;

    public volatile int j = 0;

    public void doWhile(int count) {
        while (i < count) {
            i++;
        }
    }

    public void doWhile1(int count) {
        while (j < count) {
            j++;
        }
    }

    public static void main(String[] args) {
        Main2 main2 = new Main2();
        long t1 = System.currentTimeMillis();
        main2.doWhile(1000000000);
        long t2 = System.currentTimeMillis();
        main2.doWhile1(1000000000);
        System.out.println(t2 - t1);
        System.out.println(System.currentTimeMillis() - t2);

    }

}
