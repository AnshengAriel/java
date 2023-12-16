package com.ariel.java.base.designmode.behave;

import org.junit.Assert;
import org.junit.Test;

import java.util.IdentityHashMap;
import java.util.Map;

/**
 * 策略模式
 */
public class StrategyTest {

    @Test
    public void testStrategy() {
        Environment environment = new Environment();
        int addResult = environment.get("add").calc(1, 1);
        Assert.assertEquals(addResult, 2);
        int subtractResult = environment.get("subtract").calc(1, 1);
        Assert.assertEquals(subtractResult, 0);
    }


    interface Strategy {
        String key();
        int calc(int num1,int num2);
    }

    static class AddStrategy implements Strategy {

        @Override
        public String key() {
            return "add";
        }

        @Override
        public int calc(int num1, int num2) {
            // TODO Auto-generated method stub
            return num1 + num2;
        }

    }
    static class SubtractStrategy implements Strategy {

        @Override
        public String key() {
            return "subtract";
        }

        @Override
        public int calc(int num1, int num2) {
            // TODO Auto-generated method stub
            return num1 - num2;
        }

    }

    static class Environment {
        private Map<String, Strategy> map = new IdentityHashMap<>();

        public Environment() {
            AddStrategy addStrategy = new AddStrategy();
            map.put(addStrategy.key(), addStrategy);

            SubtractStrategy subtractStrategy = new SubtractStrategy();
            map.put(subtractStrategy.key(), subtractStrategy);
        }

        public Strategy get(String key) {
            return map.get(key);
        }
    }
}