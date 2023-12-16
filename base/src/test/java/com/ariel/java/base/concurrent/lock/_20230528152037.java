package com.ariel.java.base.concurrent.lock;

import org.junit.Test;

/**
 * [CPU性能消耗观察](project\_20230526213304\src\test\java\com\ariel\lock\_20230528152037.java)
 * <a href='project\_20230526213304\src\test\java\com\ariel\lock\_20230528152037.java' style='color:green;font-weight:bold;'>运行一下</a>
 */
public class _20230528152037 {

    @Test
    public void testObject() throws InterruptedException {
        // 使用jconsole查看当前jvm进程pid，使用jstack -l pid打印锁信息
        while (true) {
            Thread.sleep(10000);
        }
    }

}
