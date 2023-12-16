package com.ariel.java.base.designmode.creator;

import org.junit.Assert;
import org.junit.Test;

/**
 * 抽象工厂模式
 */
public class AbstractFactoryTest {

    @Test
    public void testSimpleFactoryPattern() {
        VehicleFactory chineseFactory = VehicleFactory.createVehicleFactory("Chinese");

        Vehicle chineseCar = chineseFactory.getObject("car");
        Assert.assertEquals(chineseCar.myName(), "Chinese car");

        Vehicle chinesePlane = chineseFactory.getObject("plane");
        Assert.assertEquals(chinesePlane.myName(), "Chinese plane");

        Vehicle chineseBicycle = chineseFactory.getObject("bicycle");
        Assert.assertEquals(chineseBicycle.myName(), "Chinese bicycle");

        VehicleFactory englishFactory = VehicleFactory.createVehicleFactory("English");

        Vehicle englishCar = englishFactory.getObject("car");
        Assert.assertEquals(englishCar.myName(), "English car");

        Vehicle englishPlane = englishFactory.getObject("plane");
        Assert.assertEquals(englishPlane.myName(), "English plane");

        Vehicle englishBicycle = englishFactory.getObject("bicycle");
        Assert.assertEquals(englishBicycle.myName(), "English bicycle");
    }

    /**
     * 在模板工厂的基础上封装创建工厂的逻辑
     */
    public static abstract class VehicleFactory {

        public static VehicleFactory createVehicleFactory(String name) {
            if (name == null) {
                return null;
            }else if (name.equals("Chinese")) {
                return new ChineseFactory();
            } else if (name.equals("English")) {
                return new EnglishFactory();
            }else {
                return null;
            }
        }

        abstract Vehicle getObject(String name);
    }

    public static class ChineseFactory extends VehicleFactory {

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

    public static class EnglishFactory extends VehicleFactory {

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
