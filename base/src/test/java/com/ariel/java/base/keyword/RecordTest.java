package com.ariel.java.base.keyword;

import org.junit.Test;

public class RecordTest {

    @Test
    public void test() {
        User17 zs = new User17(17, "zs");
        System.out.println("zs.toString() = " + zs.toString());
        System.out.println("zs.hashCode() = " + zs.hashCode());
    }

    @Test
    public void equals() {
        User17 zs1 = new User17(17, "zs");
        User17 zs2 = new User17(17, "zs");
        System.out.println("zs1.equals(zs2) = " + zs1.equals(zs2));
    }

}
