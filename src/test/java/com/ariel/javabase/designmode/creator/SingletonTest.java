package com.ariel.javabase.designmode.creator;

import org.junit.Assert;
import org.junit.Test;

/**
 * 单例模式
 */
public class SingletonTest {

    @Test
    public void testPreSingleTon() throws NoSuchFieldException, IllegalAccessException {
        // 预加载方式
        SingleTon preSingleTon1 = new PreSingleTon().getSingleTon();
        SingleTon preSingleTon2 = new PreSingleTon().getSingleTon();
        Assert.assertEquals(preSingleTon1, preSingleTon2);

        // 懒加载+锁方式
        SingleTon lazySingleTon1 = new LazySingleTon().getSingleTon();
        SingleTon lazySingleTon2 = new LazySingleTon().getSingleTon();
        Assert.assertEquals(lazySingleTon1, lazySingleTon2);

        // 预加载+枚举方式（最佳实现）
        SingleTon enumSingleTon1 = new EnumSingleTon().getSingleTon();
        SingleTon enumSingleTon2 = new EnumSingleTon().getSingleTon();
        Assert.assertEquals(enumSingleTon1, enumSingleTon2);
    }

    interface SingleTon {
        SingleTon getSingleTon();
    }

    public static class PreSingleTon implements SingleTon {
        public static final SingleTon singleTon = new PreSingleTon();
        public SingleTon getSingleTon() {
            return singleTon;
        }
    }

    public static class LazySingleTon implements SingleTon {
        public static volatile SingleTon singleTon;
        public SingleTon getSingleTon() {
            if (singleTon == null) {
                synchronized (LazySingleTon.class) {
                    if (singleTon == null) {
                        singleTon = new LazySingleTon();
                    }
                }
            }
            return singleTon;
        }
    }

    public static class EnumSingleTon implements SingleTon {
        public SingleTon getSingleTon() {
            return SingleTonEnum.SINGLE.getSingleTon();
        }
    }

    public enum SingleTonEnum {
        SINGLE
        ;
        public final SingleTon singleTon;

        SingleTonEnum() {
            singleTon = new EnumSingleTon();
        }

        public SingleTon getSingleTon() {
            return singleTon;
        }
    }

}
