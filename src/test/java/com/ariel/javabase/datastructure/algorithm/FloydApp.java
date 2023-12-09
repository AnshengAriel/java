package com.ariel.javabase.datastructure.algorithm;

import org.junit.Test;

public class FloydApp {

    @Test
    public void todo() {
        Floyd floyd = new Floyd();
        int[][] ints = floyd.todo();
        for (int[] is : ints) {
            for (int i : is) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }

}
