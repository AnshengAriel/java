package com.ariel.java.base.keyword;

import org.junit.Test;

public class SwitchTest {

    @Test
    public void t() {
        switch (1) {
            case 1 -> System.out.println("1");
            case 2 -> System.out.println("2");
        }
        int i = switch (1) {
            case 1 -> 1;
            case 2 -> {
                yield 2;
            }
            default -> 3;
        };
        System.out.println("i = " + i);
    }

}
