package com.ariel.javabase.datastructure.stack;

import org.junit.Test;

public class PolandNotationApp {

    @Test
    public void test() {
        PolandNotation notation = new PolandNotation();
        System.out.println("3 + 4 * 5 - 6 = " + notation.calculate("3 4 + 5 * 6 -"));
        System.out.println("4 * 5 - 8 + 60 + 8 / 2 = " + notation.calculate("4 5 * 8 - 60 + 8 2 / +"));
        System.out.println(notation.calculate("4 2 5 3 * + * 8 -"));
    }

    @Test
    public void convert() {
        PolandNotation notation = new PolandNotation();
        System.out.println("4 * ( 2 + 5 * 3 ) - 8 = " + notation.convert("4 * ( 2 + 5 * 3 ) - 8"));
    }
}
