package com.ariel.javabase.keyword;

import org.junit.Test;

/**
 * volatile可见性测试
 */
public class VolatileTest {

    @Test
    public void testA() throws InterruptedException {
        Product product = new Product();
        product.setA(1);

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            product.decrementA();
        }).start();

        new Thread(() -> {
            while (product.getA() == 1) {
                // 线程没时间更新工作缓存，所以出现可见性问题，product.getA()的值永远等于1
            }
            System.out.println("product.getA() = 0");
        }).start();

        Thread.sleep(5000);
    }

    @Test
    public void testB() throws InterruptedException {
        Product product = new Product();
        product.setB(1);

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            product.decrementB();
        }).start();

        new Thread(() -> {
            while (product.getB() == 1) {
                // 不出现可见性问题，product.getB()的值被更新了
            }
            System.out.println("product.getB() = 0");
        }).start();

        Thread.sleep(5000);
    }

    @Test
    public void testC() throws InterruptedException {
        int num = 10000;
        Product product = new Product();
        product.setA(num);
        product.setB(num);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < num / 10; j++) {
                    product.decrementA();
                    product.decrementB();
                }
            }).start();
        }

        Thread.sleep(1000);

        System.out.println("product.getA() = " + product.getA());
        System.out.println("product.getB() = " + product.getB());

    }

    private static int count = 0;
    /**
     * 普通变量会导致指令重排序，重排序后一共有四种情况：
     * x = b; b = 3;    x=0
     * b = 3; x = b;    x=3
     * y = a; a = 3;    y=0
     * a = 3; y = a;    y=3
     */
    private static int x, y, a, b = 0;

    @Test
    public void testE() throws InterruptedException {
        while (true) {
            //初始化4个变量
            x = 0; y = 0; a = 0; b = 0;
            Thread threadA = new Thread(() -> {
                a = 3;
                x = b;
            });
            Thread threadB = new Thread(() -> {
                b = 3;
                y = a;
            });

            threadA.start();
            threadB.start();
            threadA.join();
            threadB.join();
            count++;

            if (x == 0 && y == 0) {
                System.out.println("执行次数:" + count);
                break;
            } else {
                System.out.println("执行次数:"+count+","+"x:"+x +" y:"+y);
            }
        }

    }

    /**
     * volatile修饰之后为指令操作增加内存屏障，保证了指令的有序性
     */
    private volatile static int x1, y1, a1, b1 = 0;

    @Test
    public void testD() throws InterruptedException {

        while (true) {
            //初始化4个变量
            x1 = 0; y1 = 0; a1 = 0; b1 = 0;
            Thread threadA = new Thread(() -> {
                a1 = 3;
                x1 = b1;
            });
            Thread threadB = new Thread(() -> {
                b1 = 3;
                y1 = a1;
            });

            threadA.start();
            threadB.start();
            threadA.join();
            threadB.join();
            count++;

            if (x1 == 0 && y1 == 0) {
                System.out.println("执行次数:" + count);
                break;
            } else {
                System.out.println("执行次数:"+count+","+"x:"+x1 +" y:"+y1);
            }
        }

    }
}
