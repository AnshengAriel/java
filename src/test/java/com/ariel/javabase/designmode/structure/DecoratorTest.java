package com.ariel.javabase.designmode.structure;

import lombok.Data;
import org.junit.Test;

/**
 * 装饰器模式
 */
public class DecoratorTest {

    @Test
    public void testDecorator() {
        Milk milk = new Milk(new Coffee());
        System.out.println(milk.getDescription());
        System.out.println(milk.cost());
    }


    @Data
    abstract static class Drink {
        public String description = "";
        private float price = 0f;

        public abstract float cost();
    }

    static class Coffee extends Drink {
        @Override
        public float cost() {
            return super.getPrice();
        }
    }

    static class Decaf extends Coffee {
        public Decaf() {
            super.setDescription("Decaf");
            super.setPrice(3.0f);
        }
    }

    static class Decorator extends Drink {
        private Drink Obj;
        public Decorator(Drink Obj) {
            this.Obj = Obj;
        };
        @Override
        public float cost() {
            return super.getPrice() + Obj.cost();
        }
        @Override
        public String getDescription() {
            return super.description + "-" + super.getPrice() + "&&" + Obj.getDescription();
        }
    }

    static class Milk extends Decorator {
        public Milk(Drink Obj) {
            super(Obj);
            super.setDescription("Milk");
            super.setPrice(2.0f);
        }
    }
}