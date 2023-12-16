package com.ariel.java.base.datastructure.algorithm;

import org.junit.Test;

import java.util.Arrays;

public class PrimApp {

    @Test
    public void handle() {
        Prim prim = new Prim();
        int[] ints = prim.handle();
        System.out.println("ints = " + Arrays.toString(ints));
    }

}
