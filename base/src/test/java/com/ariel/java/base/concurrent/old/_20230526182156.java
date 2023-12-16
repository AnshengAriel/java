package com.ariel.java.base.concurrent.old;

import org.junit.Test;

/**
 * [线程通知](project\_20230525215602\src\test\java\com\ariel\thread\_20230526182156.java)
 * <a href='project\_20230525215602\src\test\java\com\ariel\thread\_20230526182156.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230526182156 {

    @Test
    public void test() throws InterruptedException {
        MyThreadA myThreadA = new MyThreadA();
        myThreadA.start();

        new MyThreadB(myThreadA).start();

        Thread.sleep(2000);
    }
    
    public static class MyThreadA extends Thread {
        // 不使用标记的话，用interrupt方法抛出异常也可以阻止，但是如果线程不是timedwaiting状态就有风险。
        private boolean flag = true;
        
        @Override
        public void run() {
            while (flag) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println((int) (Math.random() * 100));
            }
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }

    public static class MyThreadB extends Thread {

        private final MyThreadA myThreadA;

        public MyThreadB(MyThreadA myThreadA) {
            this.myThreadA = myThreadA;
        }

        @Override
        public void run() {
            if (check()) {
                // 通知myThreadA退出
                myThreadA.setFlag(false);
                System.out.println("正在退出。。。");
            }
        }

        public boolean check() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
    }

}
