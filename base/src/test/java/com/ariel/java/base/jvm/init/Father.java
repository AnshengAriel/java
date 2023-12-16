package com.ariel.java.base.jvm.init;

public class Father {
    int i = 10;
    public Father() {
        this.print();
        i = 20;
    }
    public void print() {
        System.out.println("Father.i=" + i);
    }
}
