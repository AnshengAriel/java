package com.ariel.javabase.designmode.structure;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式
 */
public class FlyweightTest {

    @Test
    public void testPreSingleTon() {
        FlyweightFactory flyweightFactory = new FlyweightFactory();
        IFlyweight flyweight1 = flyweightFactory.getFlyweight("A");
        IFlyweight flyweight2 = flyweightFactory.getFlyweight("B");
        IFlyweight flyweight3 = flyweightFactory.getFlyweight("A");
        flyweight1.print();
        flyweight2.print();
        flyweight3.print();
        System.out.println(flyweightFactory.getFlyweightMapSize());
    }


    interface IFlyweight {
        void print();
    }

    static class Flyweight implements IFlyweight {
        private String id;
        public Flyweight(String id){
            this.id = id;
        }
        @Override
        public void print() {
            System.out.println("FlyweightTest.id = " + getId() + " ...");
        }
        public String getId() {
            return id;
        }
    }

    static class FlyweightFactory {
        private Map<String, IFlyweight> flyweightMap = new HashMap();
        public IFlyweight getFlyweight(String str){
            IFlyweight flyweight = flyweightMap.get(str);
            if(flyweight == null){
                flyweight = new Flyweight(str);
                flyweightMap.put(str, flyweight);
            }
            return flyweight;
        }
        public int getFlyweightMapSize(){
            return flyweightMap.size();
        }
    }

}
