package com.ariel.javabase.collection;

import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapTest {

    @Test
    public void test() {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        System.out.println(map);
    }
}
