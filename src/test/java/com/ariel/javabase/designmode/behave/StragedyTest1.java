package com.ariel.javabase.designmode.behave;

import org.junit.Assert;
import org.junit.Test;

/**
 * 策略模式
 */
public class StragedyTest1 {

    @Test
    public void testStrategy() {

        int addResult = StrategyFactory.get("add").calc(1, 1);
        Assert.assertEquals(addResult, 2);
        int subtractResult = StrategyFactory.get("subtract").calc(1, 1);
        Assert.assertEquals(subtractResult, 0);
    }


    enum StrategyFactory {

        ADD {
            @Override
            public int calc(int a, int b) {
                return a + b;
            }
        },
        SUB {
            @Override
            public int calc(int a, int b) {
                return a - b;
            }
        },
        ;
        public static StrategyFactory get(String name) {
            switch (name) {
                case "add" :
                    return ADD;
                case "subtract" :
                    return SUB;
                default:
                    return ADD;
            }
        }

        public abstract int calc(int a, int b);
    }
}