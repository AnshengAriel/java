package com.ariel.java.base.math;

import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalTest {

    @Test
    public void signum() {
        System.out.println("new BigDecimal(\"1\").signum() = " + new BigDecimal("1").signum());
        System.out.println("new BigDecimal(\"0\").signum() = " + new BigDecimal("0").signum());
        System.out.println("new BigDecimal(\"-1\").signum() = " + new BigDecimal("-1").signum());
        System.out.println("new BigDecimal(\"0.1\").signum() = " + new BigDecimal("0.1").signum());
    }

    @Test
    public void equals() {
        System.out.println("new BigDecimal(\"1\").equals(new BigDecimal(\"1\")) = " + new BigDecimal("1").equals(new BigDecimal("1")));
        System.out.println("new BigDecimal(\"1\").equals(new BigDecimal(\"1.0\")) = " + new BigDecimal("1").equals(new BigDecimal("1.0")));
    }
}
