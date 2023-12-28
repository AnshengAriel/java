package com.ariel.java.base.datastructure.lookup;

import com.ariel.java.base.datastructure.sort.Shell;
import org.junit.Test;

import java.util.List;

public class BinaryApp {

    @Test
    public void lookup() {
        int[] ints = {1, 4, 6, 8, 12, 34, 56, 78};
        Binary binary = new Binary();
        binary.lookup(ints, 12);
    }

    @Test
    public void timedLookup() {
        int size = 800000;
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * size);
        }

        Shell shell = new Shell();
        shell.bubbleSort(ints);

        // 使用了0ms
        Binary binary = new Binary();
        long l = System.currentTimeMillis();
        int lookup = binary.lookup(ints, ints[size / 2]);
        System.out.printf("查找目标[%s] 查找结果[%s] 查找时间[%s]", size / 2, lookup, System.currentTimeMillis() - l);
    }

    @Test
    public void noRecursion() {
        int[] ints = {1, 4, 6, 8, 12, 34, 56, 78};
        Binary binary = new Binary();
        int index = binary.noRecursion(ints, 12);
        System.out.println("index = " + index);
    }

    @Test
    public void timedNoRecursion() {
        int size = 800000;
        int[] ints = new int[size];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = (int) (Math.random() * size);
        }

        Shell shell = new Shell();
        shell.bubbleSort(ints);

        // 使用了0ms
        Binary binary = new Binary();
        long l = System.currentTimeMillis();
        int lookup = binary.noRecursion(ints, ints[size / 2]);
        System.out.printf("查找目标[%s] 查找结果[%s] 查找时间[%s]", size / 2, lookup, System.currentTimeMillis() - l);
    }

    @Test
    public void lookupSame() {
        int[] ints = {1, 4, 6, 8, 12, 12, 12, 34, 56, 78};
        Binary binary = new Binary();
        List<Integer> list = binary.lookupSame(ints, 12);
        list.forEach(System.out::println);
    }

}
