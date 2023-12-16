package com.ariel.java.base.designmode.behave;

import org.junit.Test;

/**
 * 模板模式
 */
public class TemplateMethodTest {

    @Test
    public void testPreSingleTon() {
        new EggsWithTomato().dodish();
        new Bouilli().dodish();
    }

    abstract static class Dish {
        /**
         * 具体的整个过程
         */
        protected void dodish(){
            this.preparation();
            this.doing();
            this.carriedDishes();
        }
        /**
         * 备料
         */
        public abstract void preparation();
        /**
         * 做菜
         */
        public abstract void doing();
        /**
         * 上菜
         */
        public abstract void carriedDishes ();
    }

    static class EggsWithTomato extends Dish {

        @Override
        public void preparation() {
            System.out.println("洗并切西红柿，打鸡蛋。");
        }

        @Override
        public void doing() {
            System.out.println("鸡蛋倒入锅里，然后倒入西红柿一起炒。");
        }

        @Override
        public void carriedDishes() {
            System.out.println("将炒好的西红寺鸡蛋装入碟子里，端给客人吃。");
        }

    }

    static class Bouilli extends Dish{

        @Override
        public void preparation() {
            System.out.println("切猪肉和土豆。");
        }

        @Override
        public void doing() {
            System.out.println("将切好的猪肉倒入锅中炒一会然后倒入土豆连炒带炖。");
        }

        @Override
        public void carriedDishes() {
            System.out.println("将做好的红烧肉盛进碗里端给客人吃。");
        }

    }
}