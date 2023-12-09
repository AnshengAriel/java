package com.ariel.javabase.jvm.exec;

public class FinallyTest {

    public static void main(String[] args) {
        try {
            int i = 1;
//            int j = i / 0;
            return;
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally任何时候都会执行");
        }
    }

}
