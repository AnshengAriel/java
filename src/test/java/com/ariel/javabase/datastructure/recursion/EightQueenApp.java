package com.ariel.javabase.datastructure.recursion;

import org.junit.Test;

public class EightQueenApp {

    @Test
    public void test() {
        EightQueen eightQueen = new EightQueen(8);
        eightQueen.handle(0);
    }
}
