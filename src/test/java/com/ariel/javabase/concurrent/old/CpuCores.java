package com.ariel.javabase.concurrent.old;

import org.junit.Test;

/**
 * 查看电脑配置
 */
public class CpuCores {

    @Test
    public void test() {
        // 查看可用逻辑处理器
        Runtime runtime = Runtime.getRuntime();
        int processors = runtime.availableProcessors();
        System.out.println("processors = " + processors);
    }

}
