package com.ariel.java.base.datastructure.stack;

import org.junit.Test;

public class MyStackApi {

    @Test
    public void push() {
        MyStack stack = new MyStack(10);
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        System.out.println(stack);
    }

    @Test
    public void pop() {
        MyStack stack = new MyStack(10);
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(stack.pop());
        }
    }

}
