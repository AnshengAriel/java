package com.ariel.java.base.datastructure.algorithm;

import org.junit.Test;

import java.util.Arrays;

public class KruskalApp {

    @Test
    public void handle() {
        Kruskal kruskal = new Kruskal();
        int[] ints = kruskal.handle();
        System.out.println(Arrays.toString(ints));
    }

}
