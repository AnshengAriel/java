package com.ariel.javabase.designmode;

import org.junit.Test;

/**
 * 单一职责原则
 * <a href='project\_20230424165107\src\test\java\com\ariel\design\.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class SingleResponsibilityTest {

    @Test
    public void test() {
        // 实现不同的交通工具都能跑的需求，定义Vehicle类，以name区分不同的交通工具
        Vehicle airplane = new Vehicle("airplane");
        airplane.run();
        Vehicle car = new Vehicle("car");
        car.run();
        Vehicle ship = new Vehicle("ship");
        ship.run();
    }

}
