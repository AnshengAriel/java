package com.ariel.java.base.designmode.creator;

import org.junit.Test;

/**
 * 建造者模式
 */
public class BuilderTest {

    @Test
    public void testBuilder() {
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        Product product = director.construct();
        product.show();

        // 通常情况下，使用内部类来简化建造过程
        Product product1 = Product.builder().partA("partA").partB("partB").partC("partC").build();
        product1.show();
    }

    /**
     * 产品角色：包含多个组成部件的复杂对象。
     */
    static class Product {
        private String partA;
        private String partB;
        private String partC;
        public void setPartA(String partA) {
            this.partA = partA;
        }
        public void setPartB(String partB) {
            this.partB = partB;
        }
        public void setPartC(String partC) {
            this.partC = partC;
        }
        public void show() {
            //显示产品的特性
        }

        public static Builder builder() {
            return new Product().new Builder();
        }

        class Builder {

            public Builder partA(String partA) {
                Product.this.partA = partA;
                return this;
            }

            public Builder partB(String partB) {
                Product.this.partB = partB;
                return this;
            }

            public Builder partC(String partC) {
                Product.this.partC= partC;
                return this;
            }

            public Product build() {
                return Product.this;
            }
        }
    }

    /**
     * 抽象建造者：包含创建产品各个子部件的抽象方法。
     */
    static abstract class Builder {
        //创建产品对象
        protected Product product = new Product();
        public abstract void buildPartA();
        public abstract void buildPartB();
        public abstract void buildPartC();
        //返回产品对象
        public Product getResult() {
            return product;
        }
    }

    /**
     * 具体建造者：实现了抽象建造者接口。
     */
    static class ConcreteBuilder extends Builder {
        public void buildPartA() {
            product.setPartA("建造 PartA");
        }
        public void buildPartB() {
            product.setPartB("建造 PartB");
        }
        public void buildPartC() {
            product.setPartC("建造 PartC");
        }
    }

    /**
     * 指挥者：调用建造者中的方法完成复杂对象的创建。
     */
    static class Director {
        private Builder builder;
        public Director(Builder builder) {
            this.builder = builder;
        }
        //产品构建与组装方法
        public Product construct() {
            builder.buildPartA();
            builder.buildPartB();
            builder.buildPartC();
            return builder.getResult();
        }
    }

}




