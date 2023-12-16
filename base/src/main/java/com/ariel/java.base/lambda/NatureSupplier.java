package com.ariel.java.base.lambda;

import java.util.function.IntSupplier;

public class NatureSupplier implements IntSupplier {

    private int i;

    @Override
    public int getAsInt() {
        return i++;
    }

    public static int sum(int l, int r) {
        return l + r;
    }
}


