package com.ariel.javabase.datastructure.stack;

import org.junit.Test;

public class CalculatorApp {

    @Test
    public void test() {
        Calculator calculator = new Calculator();
        System.out.println("1+1-1*2+1= " + calculator.calculate("1+1-1*2+10="));
    }

}
