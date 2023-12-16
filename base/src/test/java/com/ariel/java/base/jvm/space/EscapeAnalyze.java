package com.ariel.java.base.jvm.space;

/**
 * 逃逸分析
 * 默认开启逃逸分析：时间4ms 实例数48892
 * 手动关闭逃逸分析：-XX:-DoEscapeAnalysis 时间3ms 实例数100000
 * 可见开启逃逸分析后，jvm将会优先在栈中存放对象，基本占一半以上
 */
public class EscapeAnalyze {

    public static void main(String[] args) throws InterruptedException {
        long t = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            allocate();
        }
        System.out.println(System.currentTimeMillis() - t);

        Thread.sleep(100000);
    }

    public static void allocate() {
        EscapeAnalyze escapeAnalyze = new EscapeAnalyze();
//        byte[] bytes = new byte[1024*1024];
    }

}
