package com.ariel.java.base.concurrent.aqs;

import org.junit.Test;

import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    @Test
    public void t() {
        FutureTask<Void> task = new FutureTask<Void>(() -> null);

    }
}
