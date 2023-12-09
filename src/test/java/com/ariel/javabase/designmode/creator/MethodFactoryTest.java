package com.ariel.javabase.designmode.creator;

import org.junit.Assert;
import org.junit.Test;

/**
 * 工厂方法模式
 */
public class MethodFactoryTest {

    @Test
    public void testSimpleFactoryPattern() {
        VehicleFactory chineseFactory = new ChineseFactory();

        Vehicle chineseCar = chineseFactory.getObject("car");
        Assert.assertEquals(chineseCar.myName(), "Chinese car");

        Vehicle chinesePlane = chineseFactory.getObject("plane");
        Assert.assertEquals(chinesePlane.myName(), "Chinese plane");

        Vehicle chineseBicycle = chineseFactory.getObject("bicycle");
        Assert.assertEquals(chineseBicycle.myName(), "Chinese bicycle");

        VehicleFactory englishFactory = new EnglishFactory();

        Vehicle englishCar = englishFactory.getObject("car");
        Assert.assertEquals(englishCar.myName(), "English car");

        Vehicle englishPlane = englishFactory.getObject("plane");
        Assert.assertEquals(englishPlane.myName(), "English plane");

        Vehicle englishBicycle = englishFactory.getObject("bicycle");
        Assert.assertEquals(englishBicycle.myName(), "English bicycle");
    }

    /**
     * 把工厂方法抽象出来，方便扩展更多的工厂，而不用改动原有的逻辑
     */
    public interface VehicleFactory {

        Vehicle getObject(String name);
    }

    public static class ChineseFactory implements VehicleFactory {

        public Vehicle getObject(String name) {
            if (name == null) {
                return null;
            }else if (name.equals("car")) {
                return new ChineseCar();
            }else if (name.equals("plane")) {
                return new ChinesePlane();
            }else if (name.equals("bicycle")) {
                return new ChineseBicycle();
            }else {
                return new Vehicle();
            }
        }
    }

    public static class EnglishFactory implements VehicleFactory {

        public Vehicle getObject(String name) {
            if (name == null) {
                return null;
            }else if (name.equals("car")) {
                return new EnglishCar();
            }else if (name.equals("plane")) {
                return new EnglishPlane();
            }else if (name.equals("bicycle")) {
                return new EnglishBicycle();
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

    public static class ChineseCar extends Vehicle {

        public String myName() {
            return "Chinese car";
        }
    }

    public static class ChinesePlane extends Vehicle {

        public String myName() {
            return "Chinese plane";
        }
    }

    public static class ChineseBicycle extends Vehicle {

        public String myName() {
            return "Chinese bicycle";
        }
    }

    public static class EnglishCar extends Vehicle {

        public String myName() {
            return "English car";
        }
    }

    public static class EnglishPlane extends Vehicle {

        public String myName() {
            return "English plane";
        }
    }

    public static class EnglishBicycle extends Vehicle {

        public String myName() {
            return "English bicycle";
        }
    }
}
