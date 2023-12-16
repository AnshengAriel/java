package com.ariel.java.base.datastructure.stack;

import java.util.Arrays;

public class MyStack {

    private Integer size;

    private Integer[] array;

    private Integer top;

    public MyStack(Integer size) {
        this.size = size;
        array = new Integer[size];
        top = -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean push(Integer value) {
        if (isFull()) {
            return false;
        }
        top++;
        array[top] = value;
        return true;
    }

    public Integer pop() {
        if (isEmpty()) {
            return null;
        }
        Integer value = array[top];
        array[top] = null;
        top--;
        return value;
    }

    public Integer peek() {
        return top < 0 ? null : array[top];
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
