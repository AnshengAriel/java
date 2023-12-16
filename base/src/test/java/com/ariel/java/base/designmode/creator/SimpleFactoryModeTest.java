package com.ariel.java.base.designmode.creator;

import org.junit.Assert;
import org.junit.Test;

/**
 * 简单工厂模式
 */
public class SimpleFactoryModeTest {

    @Test
    public void testSimpleFactoryPattern() {
        SimpleFactory factory = new SimpleFactory();

        Vehicle car = factory.getObject("car");
        Assert.assertEquals(car.myName(), "car");

        Vehicle plane = factory.getObject("plane");
        Assert.assertEquals(plane.myName(), "plane");

        Vehicle bicycle = factory.getObject("bicycle");
        Assert.assertEquals(bicycle.myName(), "bicycle");
    }

    /**
     * 简单工厂
     */
    public static class SimpleFactory {

        public Vehicle getObject(String name) {
            if (name == null) {
                return null;
            }else if (name.equals("car")) {
                return new Car();
            }else if (name.equals("plane")) {
                return new Plane();
            }else if (name.equals("bicycle")) {
                return new Bicycle();
            }else {
                return new Vehicle();
            }
        }
    }

    public static class Vehicle {

        public String myName() {
            return "vehicle";
        }
    }

    public static class Car extends Vehicle {

        public String myName() {
            return "car";
        }
    }

    public static class Plane extends Vehicle {

        public String myName() {
            return "plane";
        }
    }

    public static class Bicycle extends Vehicle {

        public String myName() {
            return "bicycle";
        }
    }
}
