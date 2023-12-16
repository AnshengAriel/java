package com.ariel.java.base.jvm.init;

public class Link {

    public static final String s = "sfsd";

    public static int i = 1;

    public Runnable r = () -> System.out.println("hello runnable");

    static {
        i = 2;
    }

    {
        i = 3;
    }

    public static void main(String[] args) {
        Link link = new Link();
        System.out.println("link.i = " + link.i);
    }
}
