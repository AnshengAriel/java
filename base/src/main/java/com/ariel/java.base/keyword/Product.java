package com.ariel.java.base.keyword;

import lombok.Data;

@Data
public class Product {

    /**
     * 没有被修饰不一定出现可见性问题，线程在阻塞或者加锁后将有时间更新工作缓存，以解决可见性问题
     */
    public int a;
    /**
     * volatile修饰的变量会自动同步不同线程中工作缓存，保证变量数值统一
     */
    public volatile int b;

    public void decrementA() {
        a--;
    }

    public void decrementB() {
        b--;
    }
}
