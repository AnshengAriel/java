package com.ariel.java.base.lambda;

import java.util.function.LongSupplier;

public class FibonacciSupplier implements LongSupplier {

    private long i1 = 0, i2 = 1;

    @Override
    public long getAsLong() {
        long i = i1;
        i1 = i2;
        i2 = i1 + i;
        return i;
    }

}


