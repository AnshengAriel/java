package com.ariel.java.base.jvm.space;

import org.junit.Test;

/**
 * -XX:+PrintGCDetails  打印堆的GC信息
 */
public class HeapParamApi {

    /**
     * 当前参数：-Xms100m -Xmx200m -XX:+PrintGCDetails
     *
     * Heap
     *  PSYoungGen      total 29696K, used 5148K [0x00000000fbd80000, 0x00000000fde80000, 0x0000000100000000)
     *   eden space 25600K, 20% used [0x00000000fbd80000,0x00000000fc287008,0x00000000fd680000)
     *   from space 4096K, 0% used [0x00000000fda80000,0x00000000fda80000,0x00000000fde80000)
     *   to   space 4096K, 0% used [0x00000000fd680000,0x00000000fd680000,0x00000000fda80000)
     *  ParOldGen       total 68608K, used 0K [0x00000000f3800000, 0x00000000f7b00000, 0x00000000fbd80000)
     *   object space 68608K, 0% used [0x00000000f3800000,0x00000000f3800000,0x00000000f7b00000)
     *  Metaspace       used 3952K, capacity 4568K, committed 4864K, reserved 1056768K
     *   class space    used 428K, capacity 460K, committed 512K, reserved 1048576K
     *
     * 解释：初始化堆内存为100m，其中：
     *    (29696K + 68608K) / 1024 = 96m 是堆可用内存，包含PSYoungGen-eden、PSYoungGen-from、ParOldGen
     *    4096K / 1024 = 4m 是标记清楚算法预留的空间，包含PSYoungGen-to
     * 注：为了避免GC频繁扩缩容影响系统性能，-Xms100m -Xmx200m在生产上设置成相同的
     */
    @Test
    public void printGCDetails() {
        // runtime.totalMemory()表示的是堆中的可用内存
        Runtime runtime = Runtime.getRuntime();
        System.out.println("runtime.totalMemory() = " + runtime.totalMemory());
    }

    /**
     * 当前参数：-XX:NewRatio=4 -Xms100m -Xmx100m -XX:+PrintGCDetails
     *
     * Heap
     *  PSYoungGen      total 17920K, used 7855K [0x00000000fec00000, 0x0000000100000000, 0x0000000100000000)
     *   eden space 15360K, 51% used [0x00000000fec00000,0x00000000ff3abe68,0x00000000ffb00000)
     *   from space 2560K, 0% used [0x00000000ffd80000,0x00000000ffd80000,0x0000000100000000)
     *   to   space 2560K, 0% used [0x00000000ffb00000,0x00000000ffb00000,0x00000000ffd80000)
     *  ParOldGen       total 81920K, used 0K [0x00000000f9c00000, 0x00000000fec00000, 0x00000000fec00000)
     *   object space 81920K, 0% used [0x00000000f9c00000,0x00000000f9c00000,0x00000000fec00000)
     *  Metaspace       used 5374K, capacity 5504K, committed 5632K, reserved 1056768K
     *   class space    used 617K, capacity 659K, committed 768K, reserved 1048576K
     *
     *   说明：-XX:NewRatio=4表示老年代和新生代的比例是4:1，如上：ParOldGen（81920K）、PSYoungGen（17920K+2560K）
     */
    @Test
    public void newRatio() {

    }

    /**
     * 当前参数：-XX:SurvivorRatio=8 -Xms10m -Xmx10m -XX:+PrintGCDetails
     *
     * [GC (Allocation Failure) [PSYoungGen: 2048K->488K(2560K)] 2048K->800K(9728K), 0.0010866 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [PSYoungGen: 2536K->504K(2560K)] 2848K->1284K(9728K), 0.0009613 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * [GC (Allocation Failure) [PSYoungGen: 2552K->488K(2560K)] 3332K->1591K(9728K), 0.0009089 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     * Heap
     *  PSYoungGen      total 2560K, used 1723K [0x00000000ffd00000, 0x0000000100000000, 0x0000000100000000)
     *   eden space 2048K, 60% used [0x00000000ffd00000,0x00000000ffe34c40,0x00000000fff00000)
     *   from space 512K, 95% used [0x00000000fff00000,0x00000000fff7a020,0x00000000fff80000)
     *   to   space 512K, 0% used [0x00000000fff80000,0x00000000fff80000,0x0000000100000000)
     *  ParOldGen       total 7168K, used 1103K [0x00000000ff600000, 0x00000000ffd00000, 0x00000000ffd00000)
     *   object space 7168K, 15% used [0x00000000ff600000,0x00000000ff713ef8,0x00000000ffd00000)
     *  Metaspace       used 5374K, capacity 5568K, committed 5888K, reserved 1056768K
     *   class space    used 617K, capacity 659K, committed 768K, reserved 1048576K
     *
     *   说明：-XX:SurvivorRatio=8表示新生代中eden区和survivor区的比例是8:1:1，默认值是8
     *   注意：survivor区有个最小值512K，如果分配空间太小就不会按照8:1来进行分配
     */
    @Test
    public void survivorRatio() {

    }

    /**
     * 当前参数：-XX:-UseAdaptiveSizePolicy -Xms100m -Xmx100m -XX:+PrintGCDetails
     * 说明：按照自适应策略分配内存
     */
    @Test
    public void useAdaptiveSizePolicy() {

    }

    /**
     * 当前参数：-Xmn20m -Xms100m -Xmx100m -XX:+PrintGCDetails
     * 说明：设置新生代空间大小，优先级高于-XX:NewRatio参数
     */
    @Test
    public void xmn() {

    }

}
