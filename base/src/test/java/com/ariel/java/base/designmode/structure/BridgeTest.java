package com.ariel.java.base.designmode.structure;

import org.junit.Test;

/**
 * 桥接模式
 */
public class BridgeTest {

    @Test
    public void testPreSingleTon() {
        Oppo oppo = new Oppo();
        oppo.setSoftware(new AppStore());
        oppo.run();
    }

    interface Software {
        void run();

    }

    static class AppStore implements Software {

        @Override
        public void run() {
            System.out.println("run app store");
        }
    }

    static class Camera implements Software {

        @Override
        public void run() {
            System.out.println("run camera");
        }
    }

    abstract static class Phone {

        protected Software software;

        public void setSoftware(Software software) {
            this.software = software;
        }

        public abstract void run();

    }
    static class Oppo extends Phone {

        @Override
        public void run() {
            software.run();
        }
    }
    static class Vivo extends Phone {

        @Override
        public void run() {
            software.run();
        }
    }
}