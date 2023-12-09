package com.ariel.javabase.jvm.init;

/**
 * 字节码
 * 0 iconst_2
 * 1 putstatic #3 <com/ariel/jvm/ClassClinit.i : I>
 * 4 iconst_1
 * 5 putstatic #3 <com/ariel/jvm/ClassClinit.i : I>
 * 8 return
 */
public class ClassClinit {

    public int j;

    {
        j = 20;
        j = 10;
        j = 30;
    }

    static {
        i = 2;
    }

    public static int i = 1;

    public static void main(String[] args) {
        // 1 or 2
        System.out.println(i); // 1
        System.out.println(new ClassClinit().j);
    }

}
