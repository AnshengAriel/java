package com.ariel.javabase.jvm;

public class VilenessVerify {

    private static int SUM = 0;

    private static boolean FLAY = true;

    public static void main(String[] args) throws InterruptedException {

        Thread thread_1 = new Thread(() -> {
            while (FLAY) {
                SUM++;
            }
//            boolean flag = false;
//            while (true) {
//                if (!FLAY) {
//                    break;
//                }
//                if (flag) {
//                    break;
//                }
//                SUM++;
//            }
            System.out.println(Thread.currentThread().getName() + "end");
        });
        Thread thread_2 = new Thread(() -> {
            FLAY = false;
        });
        thread_1.start();
        Thread.sleep(2);
        thread_2.start();
        thread_1.join();
        thread_2.join();
        System.out.println(SUM);
    }

}
