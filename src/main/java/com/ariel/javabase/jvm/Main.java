package com.ariel.javabase.jvm;

import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) {
        Main2 main = new Main2();
        String hello1 = "hello1";
        String hello2 = "hello2";
        String hello3 = "hello3";
        String hello4 = "hello4";
        System.out.println("hash(hello1) = " + hash(hello1));
        System.out.println("hash(hello2) = " + hash(hello2));
        System.out.println("hash(hello3) = " + hash(hello3));
        System.out.println("hash(hello4) = " + hash(hello4));
    }

    public static boolean equalsStr(String s, String t) {
        try {
            Field field = String.class.getDeclaredField("value");
            field.setAccessible(true);
            return field.get(s) == field.get(t);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static int hash(String s) {
        try {
            Field field = String.class.getDeclaredField("value");
            field.setAccessible(true);
            return field.get(s).hashCode();
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static void alterStr(String s, char[] chars) {
        try {
            Field field = String.class.getDeclaredField("value");
            field.setAccessible(true);
            char[] sChars = (char[]) field.get(s);
            System.arraycopy(chars, 0, sChars, 0, sChars.length);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

}